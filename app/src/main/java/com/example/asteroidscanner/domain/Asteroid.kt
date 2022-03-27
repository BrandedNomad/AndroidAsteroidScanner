package com.example.asteroidscanner.domain

import com.example.asteroidscanner.data.database.CloseApproachData
import com.example.asteroidscanner.data.database.EstimatedDiameter

enum class AsteroidStatus{
    NORMAL,
    POTENTIALY_HAZARDOUS
}

//data class Asteroid(val name: String, val date: String, val status:AsteroidStatus)

//data class Kilometers constructor(
//    val estimated_diameter_min:Float,
//    val estimated_diameter_max:Float
//)
//
//data class EstimatedDiameter constructor(
//    val kilometers:Kilometers
//)
//
//data class RelativeVelocity constructor(
//    val kilometers_per_second: String
//)
//
//data class MissDistance constructor(
//    val kilometers:String
//)
//
//data class CloseApproachData constructor (
//    val close_approach_date: String,
//    val relative_velocity:RelativeVelocity,
//    val miss_distance: MissDistance
//)

data class Asteroid(
    val id:String,
    val name:String,
    val absolute_magnitude:Float,
    val estimated_diameter: Float,
    val close_approach_date: String,
    val relative_velocity: String,
    val distance_from_earth: String,
    val status: Boolean
)

