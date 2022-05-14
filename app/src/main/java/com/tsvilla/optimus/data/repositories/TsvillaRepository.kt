package com.tsvilla.optimus.data.repositories

import android.util.Log
import com.tsvilla.optimus.R
import com.tsvilla.optimus.data.services.TsvillaApi
import com.tsvilla.optimus.domain.ITsvillaRepository
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Maybe
import org.koin.java.KoinJavaComponent.inject

class TsvillaRepository : ITsvillaRepository {

    private val tsvillaApi: TsvillaApi by inject(TsvillaApi::class.java)
    private val token: String = ""

    companion object {
        private const val TAG = "TsvillaRepository"
    }

    override fun sendBPM(bpm: Int): Maybe<Boolean> = Maybe.create { emitter ->
        tsvillaApi.sendBPM(token, bpm)
        //    .subscribe(emitter::onNext)
    }

}