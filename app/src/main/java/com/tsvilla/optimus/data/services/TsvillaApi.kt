package com.tsvilla.optimus.data.services

import io.reactivex.Maybe
import io.reactivex.Single
import retrofit2.http.GET

interface TsvillaApi {

    @GET("/")
    fun sendBPM(token: String, bpm: Int) : Single<Boolean>
}