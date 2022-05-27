package com.tsvilla.optimus.presentation.viewmodel

import android.content.Context
import android.content.Context.SENSOR_SERVICE
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import com.tsvilla.optimus.data.repositories.TsvillaRepository
import com.tsvilla.optimus.domain.ITsvillaRepository
import com.tsvilla.optimus.presentation.model.MonitorState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.java.KoinJavaComponent.inject

class MonitorViewModel : SensorEventListener, BaseViewModel<MonitorState> (
    MonitorState()
) {
    private val repository: ITsvillaRepository by inject(TsvillaRepository::class.java)
    private val sensorManager: SensorManager by inject(SensorManager::class.java)

    private val timeMillis: Long = 500 // Desired delay between events in microseconds

    init {
        generateAndSendRandomBpm()
        //initSensor()
    }

    private fun initSensor() {
        val heartSensor = sensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE)
        sensorManager.registerListener(this, heartSensor, timeMillis.toInt())
    }


    private fun generateAndSendRandomBpm() {
        CoroutineScope(IO).launch {
            delay(timeMillis)
            CoroutineScope(Main).launch {
                val bpm = (0..100).random()
                val state = MonitorState(currentBPM=bpm)
                _state.onNext(state)
                generateAndSendRandomBpm()
            }
        }
    }

    private fun sendBpm(bpm: Int) {
        repository.sendBPM(bpm)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { _ ->
                val state = MonitorState(currentBPM=bpm)
                _state.onNext(state)
            }
    }

    override fun onSensorChanged(event: SensorEvent) {
        when(event.sensor.type) {
            Sensor.TYPE_HEART_RATE -> {
                val bpm = event.values[0].toInt()
                val state = MonitorState(currentBPM=bpm)
                _state.onNext(state)
           //     sendBpm(bpm.toInt())
            }
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }
}