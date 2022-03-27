package com.example.asteroidscanner.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.asteroidscanner.domain.Asteroid

class MainViewModel: ViewModel() {

    private var _listItems = MutableLiveData<ArrayList<Asteroid>>()
    val listItems: LiveData<ArrayList<Asteroid>>
        get() = _listItems

    init{
        _listItems.value = dummyData
    }

    override fun onCleared() {
        super.onCleared()
    }
}