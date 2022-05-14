package com.tsvilla.optimus.di

import com.tsvilla.optimus.presentation.viewmodel.HomeViewModel
import com.tsvilla.optimus.presentation.viewmodel.MonitorViewModel
import com.tsvilla.optimus.presentation.viewmodel.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val presentationModule = module {

    viewModel { MonitorViewModel() }
    viewModel { SettingsViewModel() }
    viewModel { HomeViewModel() }


}