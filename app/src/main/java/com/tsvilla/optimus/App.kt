package com.tsvilla.optimus

import android.app.Application
import com.tsvilla.optimus.di.apiModule
import com.tsvilla.optimus.di.presentationModule
import com.tsvilla.optimus.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application(){

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            androidLogger()

            modules(apiModule)
            modules(repositoryModule)
            modules(presentationModule)
        }
    }
}