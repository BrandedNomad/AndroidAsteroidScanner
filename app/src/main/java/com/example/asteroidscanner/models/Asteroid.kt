package com.example.asteroidscanner.models

enum class AsteroidStatus{
    NORMAL,
    POTENTIALY_HAZARDOUS
}

data class Asteroid(val name: String, val date: String, val status:AsteroidStatus)