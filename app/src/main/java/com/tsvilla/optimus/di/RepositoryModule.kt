package com.tsvilla.optimus.di
import com.tsvilla.optimus.data.repositories.TsvillaRepository
import org.koin.dsl.module

val repositoryModule = module {

    single {
        TsvillaRepository()
    }

}