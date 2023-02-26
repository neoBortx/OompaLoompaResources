package com.bortxapps.infrastructure.remote.service

import com.bortxapps.infrastructure.remote.api.OompaLoompaInfoApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object OompaLoompaInfoService {

    private const val BASE_URL = "https://2q2woep105.execute-api.eu-west-1.amazonaws.com/napptilus/"

    private val httpClient = OkHttpClient.Builder().apply {
        addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
    }
    val api: OompaLoompaInfoApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient.build())
        .build()
        .create(OompaLoompaInfoApi::class.java)
}