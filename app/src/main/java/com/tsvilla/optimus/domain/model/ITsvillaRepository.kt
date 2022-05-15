package com.tsvilla.optimus.domain

import io.reactivex.Flowable
import io.reactivex.Maybe

interface ITsvillaRepository {
    fun sendBPM(bpm: Int): Flowable<String>
}