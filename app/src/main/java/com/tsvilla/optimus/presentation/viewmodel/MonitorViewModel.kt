package com.tsvilla.optimus.presentation.viewmodel

import com.tsvilla.optimus.data.repositories.TsvillaRepository
import com.tsvilla.optimus.domain.ITsvillaRepository
import com.tsvilla.optimus.presentation.model.MonitorState
import org.koin.java.KoinJavaComponent.inject

class MonitorViewModel : BaseViewModel<MonitorState>(
    MonitorState()
) {
    private val repository: ITsvillaRepository by inject(TsvillaRepository::class.java)
}