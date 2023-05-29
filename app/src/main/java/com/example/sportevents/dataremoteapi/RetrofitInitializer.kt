package com.example.sportevents.dataremoteapi

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.component.KoinComponent
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInitializer: KoinComponent {

    const val BASE_URL = "https://62a054d8202ceef7086aed64.mockapi.io/"
    const val REST_API_VERSION = "api/v1/"

    inline fun <reified T : Any> initApi(
        okHttpClient: OkHttpClient
    ): T {
        val mapper = ObjectMapper()
            .registerKotlinModule()
            .configure(
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

        val retrofitBaseUrl = BASE_URL + REST_API_VERSION
        return Retrofit.Builder()
            .addConverterFactory(JacksonConverterFactory.create(mapper))
            .client(okHttpClient)
            .baseUrl(retrofitBaseUrl)
            .build()
            .create(T::class.java)
    }

    private const val DEFAULT_TIMEOUT = 10000

    fun getOkHttpBuilder(): OkHttpClient.Builder {
        val dispatcher = Dispatcher()
        dispatcher.maxRequests = 1
        val builder = OkHttpClient.Builder()
            .callTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
            .dispatcher(dispatcher)
            .retryOnConnectionFailure(true)

            val httpLoggingInterceptor = HttpLoggingInterceptor()
            builder.addInterceptor(httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            })
        return builder
    }
}