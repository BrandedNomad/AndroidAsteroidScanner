package com.example.asteroidscanner.data.network.interfaces

import com.example.asteroidscanner.data.network.responseclasses.GetAsteroidResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//Interface used for JSON conversion
interface AsteroidService {

    @GET("neo/rest/v1/feed")
    fun getAsteroidList(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
        @Query("api_key") apiKey: String
    ): Call<GetAsteroidResponse>
}