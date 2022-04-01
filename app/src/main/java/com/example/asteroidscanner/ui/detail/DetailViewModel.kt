package com.example.asteroidscanner.ui.detail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.asteroidscanner.domain.Asteroid

class DetailViewModel(private val asteroid: Asteroid,val application: Application):ViewModel() {

    private var _selectedAsteroid = MutableLiveData<Asteroid>()
    val selectedAsteroid: LiveData<Asteroid>
        get() = _selectedAsteroid

    init{
        _selectedAsteroid.value = asteroid
    }

    override fun onCleared() {
        super.onCleared()
    }
}