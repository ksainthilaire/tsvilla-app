package com.tsvilla.optimus.data.services

import com.tsvilla.optimus.data.model.SendBPMRequest
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface TsvillaApi {

    @POST("/")
    fun sendBPM(@Body body: SendBPMRequest) : Observable<String>
}