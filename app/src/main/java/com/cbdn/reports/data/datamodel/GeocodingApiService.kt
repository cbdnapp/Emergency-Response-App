package com.cbdn.reports.data.datamodel

import retrofit2.http.GET
import retrofit2.http.Query

interface GeocodingApiService {
    @GET("geocode/json")
    suspend fun geocodeLatLng(
        @Query("latlng") latlng: String,
        @Query("location_type") locationType: String,
        @Query("result_type") resultType: String,
        @Query("key") apiKey: String,
    ): GeocodingResponse
}
