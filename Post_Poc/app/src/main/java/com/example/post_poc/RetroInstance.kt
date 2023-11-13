package com.example.post_poc

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {
    companion object{
        private const val baseURL = "https://us-central1-emergency-response-app-restapi.cloudfunctions.net/app/"
        private val logger: HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        private val okHttp: OkHttpClient.Builder = OkHttpClient.Builder().addInterceptor(logger)

        private val builder: Retrofit.Builder = Retrofit.Builder().baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp.build())

        private val retrofit: Retrofit = builder.build()
        fun<T> buildService(serviceType: Class<T>): T{
            return retrofit.create(serviceType)
        }

//        fun getRetroInstance(): Retrofit{
//            val logging = HttpLoggingInterceptor()
//            logging.level = (HttpLoggingInterceptor.Level.BODY)
//            val client = OkHttpClient.Builder()
//            client.addInterceptor(logging)
//            return Retrofit.Builder()
//                .baseUrl(baseURL)
//                .client(client.build())
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//        }
    }
}