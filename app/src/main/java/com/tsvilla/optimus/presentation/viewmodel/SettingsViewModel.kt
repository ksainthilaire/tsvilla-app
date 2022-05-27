package com.tsvilla.optimus.presentation.viewmodel

import com.tsvilla.optimus.data.repositories.TsvillaRepository
import com.tsvilla.optimus.domain.ITsvillaRepository
import com.tsvilla.optimus.presentation.model.SettingsState
import org.koin.java.KoinJavaComponent.inject

class SettingsViewModel : BaseViewModel<SettingsState>(
    SettingsState()
) {
    private val repository: ITsvillaRepository by inject(TsvillaRepository::class.java)
}