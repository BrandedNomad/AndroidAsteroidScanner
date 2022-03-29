package com.example.asteroidscanner.data.repository

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.asteroidscanner.MainActivity
import com.example.asteroidscanner.data.database.AsteroidDatabase
import com.example.asteroidscanner.data.database.asDomainModel
import com.example.asteroidscanner.data.network.*
import com.example.asteroidscanner.domain.Asteroid
import com.example.asteroidscanner.shared.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Response.error
import retrofit2.await
import java.lang.RuntimeException
import java.security.AccessController.getContext


class AsteroidRepository(private val database:AsteroidDatabase){

    val asteroidsList: LiveData<List<Asteroid>> = Transformations.map(database.asteroidDao.getAsteroids()){
        it.asDomainModel()
    }

    //TODO: Add date variables to replace hardcoded dates
    suspend fun refreshAsteroids(){

        withContext(Dispatchers.IO){
            val response = Network.asteroidsAPI.getAsteroidList("2022-03-27","2022-03-27", Constants.API_KEY).await()
            //TODO: Defensive block
            database.asteroidDao.insertAll(*response.asDataBaseModel())

        }
    }
}



