package com.example.post_poc

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RetroService {
    @POST("reports")
    @Headers("Content-Type:application/json")
    fun createReport(@Body params: Report): Call<ReportResponse>
}