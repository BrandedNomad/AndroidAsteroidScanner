package com.example.asteroidscanner.data.network.interfaces

import com.example.asteroidscanner.data.network.responseclasses.GetImageResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageService {
    @GET("planetary/apod")
    fun getImageOfTheDay(
        @Query("api_key") apiKey:String
    ) : Call<GetImageResponse>
}