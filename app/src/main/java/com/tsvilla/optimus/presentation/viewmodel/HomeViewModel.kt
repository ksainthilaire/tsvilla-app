package com.tsvilla.optimus.presentation.viewmodel

import com.tsvilla.optimus.data.repositories.TsvillaRepository
import com.tsvilla.optimus.domain.ITsvillaRepository
import com.tsvilla.optimus.presentation.model.HomeState
import com.tsvilla.optimus.presentation.model.MonitorState
import org.koin.java.KoinJavaComponent.inject

class HomeViewModel : BaseViewModel<HomeState>(
    HomeState()
) {
    private val repository: ITsvillaRepository by inject(TsvillaRepository::class.java)
}