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
import androidx.lifecycle.viewModelScope
import com.tsvilla.optimus.data.repositories.TsvillaRepository
import com.tsvilla.optimus.domain.ITsvillaRepository
import com.tsvilla.optimus.presentation.model.MonitorState
import com.tsvilla.optimus.presentation.utils.LifecycleChecker
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.java.KoinJavaComponent.inject

class MonitorViewModel : SensorEventListener, BaseViewModel<MonitorState>(
    MonitorState(isPaused = false)
) {

    companion object {
        const val TAG = "MonitorViewModel"
    }

    private val repository: ITsvillaRepository by inject(TsvillaRepository::class.java)
    private val sensorManager: SensorManager by inject(SensorManager::class.java)

    private val timeMillis: Long = 500// Desired delay between events in microseconds
    private val timeBeforeSending: Long = 10000

    init {
        //generateAndSendRandomBpm()
        initSensor()
    }

    private fun initSensor() {
        val heartSensor = sensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE)
        sensorManager.registerListener(this, heartSensor, timeMillis.toInt())
        sendBpm()
    }


    private fun generateAndSendRandomBpm() {
        viewModelScope.launch {

            delay(timeMillis)
            Log.d("TAG", "test ${isPaused()}")

            viewModelScope.launch {
                if (!isPaused()) {
                    val bpm = (0..100).random()

                    val state = MonitorState(currentBPM = bpm, isPaused = isPaused())

                    _state.onNext(state)
                }
                generateAndSendRandomBpm()
            }
        }
    }

    fun isPaused(): Boolean = if (_state.value != null) _state.value!!.isPaused else false
    fun currentBPM(): Int = if (_state.value != null) _state.value!!.currentBPM else 0

    fun switchSending() {
        val state = MonitorState(currentBPM = currentBPM(), isPaused = !isPaused())

        _state.onNext(state)
    }


    private fun sendBpm() {
        viewModelScope.launch {
            delay(timeBeforeSending)
            if (isPaused()) return@launch sendBpm()
            val currentState = _state.value ?: return@launch sendBpm()
            val bpm = currentState!!.currentBPM
            repository.sendBPM(bpm)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ _ ->
                    val state = MonitorState(currentBPM = bpm)
                    _state.onNext(state)
                }, { tr -> Log.d(TAG, tr.toString()) })
        }
    }

    override fun onSensorChanged(event: SensorEvent) {
        when (event.sensor.type) {
            Sensor.TYPE_HEART_RATE -> {
                if (isPaused()) return;
                val bpm = event.values[0].toInt()
                val state = MonitorState(currentBPM = bpm)
                _state.onNext(state)
            }
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }
}