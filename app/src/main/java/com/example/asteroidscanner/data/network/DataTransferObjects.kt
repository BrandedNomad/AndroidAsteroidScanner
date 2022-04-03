package com.example.asteroidscanner.data.network


import android.util.Log
import androidx.room.Database
import com.example.asteroidscanner.data.database.DatabaseAsteroid

import com.example.asteroidscanner.data.network.responseclasses.GetAsteroidResponse
import com.example.asteroidscanner.data.network.responseclasses.GetImageResponse
import com.example.asteroidscanner.data.network.responseclasses.ImageContainer
import com.example.asteroidscanner.domain.Asteroid
import com.example.asteroidscanner.domain.DailyImage
import com.example.asteroidscanner.shared.Utils


// DomainModel and DatabaseModels mapping functions
fun GetAsteroidResponse.asDomainModel():List<Asteroid>{
    return nearEarthObjects[Utils.getCurrentDate()]!!.map{
        Asteroid(
            id = it.id,
            name = it.name,
            date = it.name,
            absolute_magnitude = it.absoluteMagnitudeH,
            close_approach_date = it.closeApproachData.get(0).closeApproachDate,
            relative_velocity = it.closeApproachData.get(0).relativeVelocity.kilometersPerSecond,
            estimated_diameter = it.estimatedDiameter.kilometers.estimatedDiameterMax,
            distance_from_earth = it.closeApproachData.get(0).missDistance.astronomical,
            status = it.isPotentiallyHazardousAsteroid
        )
    }
}

fun GetAsteroidResponse.asDataBaseModel():Array<DatabaseAsteroid>{

    var listToProcess: ArrayList<DatabaseAsteroid> = arrayListOf()

    for(item in nearEarthObjects.entries.iterator()){
        val array = nearEarthObjects[item.key]!!.map{
            DatabaseAsteroid(
                id = it.id,
                name = it.name,
                date = item.key,
                absolute_magnitude = it.absoluteMagnitudeH,
                close_approach_date = it.closeApproachData.get(0).closeApproachDate,
                relative_velocity = it.closeApproachData.get(0).relativeVelocity.kilometersPerSecond,
                estimated_diameter = it.estimatedDiameter.kilometers.estimatedDiameterMax,
                distance_from_earth = it.closeApproachData.get(0).missDistance.astronomical,
                status = it.isPotentiallyHazardousAsteroid
            )
        }.toTypedArray()

        array.map{
            listToProcess.add(it)
        }
    }

    val returnData = listToProcess.map{
        it
    }.toTypedArray()

    return returnData
}



