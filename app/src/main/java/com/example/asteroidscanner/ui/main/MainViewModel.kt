package com.example.asteroidscanner.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private var _listItems = MutableLiveData<ArrayList<String>>()
    val listItems: LiveData<ArrayList<String>>
        get() = _listItems

    init{
        _listItems.value = dummyData
    }

    override fun onCleared() {
        super.onCleared()
    }
}