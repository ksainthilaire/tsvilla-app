package com.tsvilla.optimus.di

import android.content.res.Resources
import com.google.gson.GsonBuilder
import com.tsvilla.optimus.R
import com.tsvilla.optimus.data.services.TsvillaApi
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val apiModule = module {

    single<OkHttpClient> {
        OkHttpClient().newBuilder().addInterceptor {
            val request = it.request()
                .newBuilder()
                .build()
            it.proceed(request)
        }.callTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }




    single<Retrofit> {
        val httpClient = get<OkHttpClient>()
        val resources = get<Resources>()

        val gson = GsonBuilder()
            .setLenient()
            .create()
     val baseUrl = resources.getString(R.string.api_tsvilla_base_url)

        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    single<TsvillaApi> {
        get<Retrofit>().create(TsvillaApi::class.java)
    }
}