package com.example.asteroidscanner.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.asteroidscanner.data.database.getDatabase
import com.example.asteroidscanner.data.repository.AsteroidRepository
import com.example.asteroidscanner.domain.Asteroid
import kotlinx.coroutines.launch

class MainViewModel(application: Application): ViewModel() {

    private val database = getDatabase(application)
    private val asteroidRepository = AsteroidRepository(database)


    init{
        viewModelScope.launch{
            asteroidRepository.refreshAsteroids()
        }
    }

    val asteroidList = asteroidRepository.asteroidsList

    override fun onCleared() {
        super.onCleared()
    }
}