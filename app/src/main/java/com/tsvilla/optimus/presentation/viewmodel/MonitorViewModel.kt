package com.tsvilla.optimus.presentation.viewmodel

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
import org.koin.java.KoinJavaComponent.inject

class MonitorViewModel : BaseViewModel<MonitorState>(
    MonitorState()
) {
    private val repository: ITsvillaRepository by inject(TsvillaRepository::class.java)

    init {
        generateAndSendRandomBpm()
    }

    private fun generateAndSendRandomBpm() {
        CoroutineScope(IO).launch {
            delay(300)
            CoroutineScope(Main).launch {
                val bpm = (0..100).random()
                sendBpm(bpm)
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
}