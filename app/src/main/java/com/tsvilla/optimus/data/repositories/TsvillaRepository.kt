package com.tsvilla.optimus.data.repositories

import android.content.res.Resources
import android.util.Log
import com.tsvilla.optimus.R
import com.tsvilla.optimus.data.model.SendBPMRequest
import com.tsvilla.optimus.data.services.TsvillaApi
import com.tsvilla.optimus.domain.ITsvillaRepository
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Maybe
import org.koin.java.KoinJavaComponent.inject

class TsvillaRepository : ITsvillaRepository {

    private val tsvillaApi: TsvillaApi by inject(TsvillaApi::class.java)
    private val resources: Resources by inject(Resources::class.java)
    private val token: String = resources.getString(R.string.api_tsvilla_secret)

    companion object {
        private const val TAG = "TsvillaRepository"
    }


    override fun sendBPM(bpm: Int): Flowable<String> = Flowable.create({ emitter ->
        tsvillaApi.sendBPM(SendBPMRequest(token, bpm))
            .subscribe({ emitter.onNext(it) }, { tr ->
                Log.e(TAG, "Error while send BPM", tr)
            })
       }, BackpressureStrategy.BUFFER)
}