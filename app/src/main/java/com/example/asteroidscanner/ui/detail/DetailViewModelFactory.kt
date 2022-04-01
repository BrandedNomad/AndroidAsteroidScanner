package com.example.asteroidscanner.ui.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.asteroidscanner.domain.Asteroid
import java.lang.IllegalArgumentException

class DetailViewModelFactory(val asteroid: Asteroid, val application: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DetailViewModel::class.java)){
            return DetailViewModel(asteroid,application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}