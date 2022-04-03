package com.example.asteroidscanner.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.asteroidscanner.data.database.AsteroidDatabase
import com.example.asteroidscanner.data.database.asDomainModel
import com.example.asteroidscanner.data.network.*
import com.example.asteroidscanner.data.network.responseclasses.GetImageResponse
import com.example.asteroidscanner.domain.Asteroid
import com.example.asteroidscanner.domain.DailyImage
import com.example.asteroidscanner.shared.Constants
import com.example.asteroidscanner.shared.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.await


class AsteroidRepository(private val database:AsteroidDatabase){




    val asteroidsList: LiveData<List<Asteroid>> = Transformations.map(database.asteroidDao.getAsteroids(Utils.getCurrentDate())){
        it.asDomainModel()
    }

    suspend fun getDailyImage():GetImageResponse{
        try{
            val returnData:GetImageResponse = withContext(Dispatchers.IO){
                val response = Network.imageOfTheDayAPI.getImageOfTheDay(Constants.API_KEY).await()
                return@withContext response

            }
            return returnData
        }catch (exception:Exception){
            return GetImageResponse("none","none","image","none","none","none")
        }

    }

    suspend fun removeOld(currentDate:String){
        withContext(Dispatchers.IO){
            try{
                database.asteroidDao.removeOld(currentDate)
            }catch(exception:Exception){
                Log.e("Repository","Failed to remove old asteroids")
            }
        }
    }

    //TODO: Add date variables to replace hardcoded dates
    suspend fun refreshAsteroids(currentDate:String){
        withContext(Dispatchers.IO){
            try{
                val response = Network.asteroidsAPI.getAsteroidList(currentDate, Constants.API_KEY).await()
                database.asteroidDao.insertAll(*response.asDataBaseModel())
            }catch(exception:Exception){
                Log.e("Repository","Connection Error: RefreshAsteroids")
            }
        }
    }
}



