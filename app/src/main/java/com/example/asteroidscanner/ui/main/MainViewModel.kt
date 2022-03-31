package com.example.asteroidscanner.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.asteroidscanner.data.database.getDatabase
import com.example.asteroidscanner.data.network.Network
import com.example.asteroidscanner.data.network.responseclasses.GetImageResponse
import com.example.asteroidscanner.data.repository.AsteroidRepository
import com.example.asteroidscanner.domain.Asteroid
import com.example.asteroidscanner.domain.DailyImage
import com.example.asteroidscanner.shared.Constants
import com.example.asteroidscanner.shared.Utils
import kotlinx.coroutines.launch
import retrofit2.await

class MainViewModel(application: Application): ViewModel() {

    private val database = getDatabase(application)
    private val asteroidRepository = AsteroidRepository(database)
    private var _dailyImage = MutableLiveData<GetImageResponse>()

    private val _navigateToSelectedAsteroid = MutableLiveData<Asteroid?>()
    val navigateToSelectedAsteroid:LiveData<Asteroid?>
        get() = _navigateToSelectedAsteroid


    init{

        viewModelScope.launch{
            asteroidRepository.refreshAsteroids(Utils.getCurrentDate())
            var responseImage = Network.imageOfTheDayAPI.getImageOfTheDay(Constants.API_KEY).await()
            if(responseImage.mediaType == "image"){
                _dailyImage.value = responseImage
            }else{
                _dailyImage.value = GetImageResponse(
                    title = "Default",
                    url = "https://apod.nasa.gov/apod/image/2001/STSCI-H-p2006a-h-1024x614.jpg",
                    mediaType = "image"
                )
            }
            

        }
    }

    val dailyImage:LiveData<GetImageResponse>
        get() = _dailyImage

    val asteroidList = asteroidRepository.asteroidsList
    //val imageOfTheDay: asteroidRepository.imageOfTheDay

    fun displayAsteroidDetails(asteroid:Asteroid){
        _navigateToSelectedAsteroid.value = asteroid
    }

    fun displayAsteroidDetailsComplete(){
        _navigateToSelectedAsteroid.value = null
    }

    override fun onCleared() {
        super.onCleared()
    }
}