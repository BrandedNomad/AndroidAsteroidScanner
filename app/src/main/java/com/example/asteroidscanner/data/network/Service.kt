package com.example.asteroidscanner.data.network


import com.example.asteroidscanner.data.network.interfaces.AsteroidService
import com.example.asteroidscanner.data.network.interfaces.ImageService
import com.example.asteroidscanner.shared.Constants
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query






//MOSHI used to convert JSON to response object
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

//The Retrofit object used to make network requests
private val retrofit = Retrofit.Builder()
    .baseUrl(Constants.BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .build()


//NETWORK object that will be used to make API calls
object Network {
    val asteroidsAPI: AsteroidService = retrofit.create(AsteroidService::class.java)
    val imageOfTheDayAPI: ImageService = retrofit.create(ImageService::class.java)
}