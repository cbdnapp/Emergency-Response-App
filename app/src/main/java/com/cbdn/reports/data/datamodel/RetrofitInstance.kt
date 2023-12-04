package com.cbdn.reports.data.datamodel

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://maps.googleapis.com/maps/api/"
    private val logger: HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val okHttp: OkHttpClient.Builder = OkHttpClient.Builder().addInterceptor(logger)
    val geocodingApi: GeocodingApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp.build())
            .build()
            .create(GeocodingApiService::class.java)
    }
}