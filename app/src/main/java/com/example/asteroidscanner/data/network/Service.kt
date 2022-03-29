package com.example.asteroidscanner.data.network


import com.example.asteroidscanner.shared.Constants
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
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
}