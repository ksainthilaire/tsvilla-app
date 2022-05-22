package com.tsvilla.optimus.di

import android.content.Context
import android.content.Context.SENSOR_SERVICE
import android.hardware.SensorManager
import androidx.core.content.ContextCompat.getSystemService
import com.tsvilla.optimus.presentation.viewmodel.HomeViewModel
import com.tsvilla.optimus.presentation.viewmodel.MonitorViewModel
import com.tsvilla.optimus.presentation.viewmodel.SettingsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val presentationModule = module {

    viewModel { MonitorViewModel() }
    viewModel { SettingsViewModel() }
    viewModel { HomeViewModel() }

    single {
        androidContext().resources
    }

    single {
        val context = androidContext().applicationContext
        val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorManager
    }


}