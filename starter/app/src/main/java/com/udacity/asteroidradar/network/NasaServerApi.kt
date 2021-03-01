package com.udacity.asteroidradar.network

import com.udacity.asteroidradar.network.response.ImageOfDayResponse
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaServerApi {
    @GET("planetary/apod")
    suspend fun loadImageOfADay(@Query("api_key") apiKey: String) : ImageOfDayResponse

    @GET("neo/rest/v1/feed")
    suspend fun loadAsteroidsFeed(
        @Query("api_key") apiKey: String,
        @Query("start_date") startDate: String?,
        @Query("end_date") endDate: String?
    ) : ResponseBody

}