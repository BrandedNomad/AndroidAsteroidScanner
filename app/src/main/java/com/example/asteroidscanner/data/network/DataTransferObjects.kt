package com.example.asteroidscanner.data.network

import androidx.room.Database
import com.example.asteroidscanner.data.database.DatabaseAsteroid
import com.example.asteroidscanner.domain.Asteroid
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass



//The network container that will contain the Response item
data class NetworkAsteroidContainer(
    val asteroids: List<GetAsteroidResponse>
)


//Domain and database mapping functions
fun NetworkAsteroidContainer.asDomainModel(): List<Asteroid>{

    return asteroids.get(0).nearEarthObjects.x20220327.map{
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

fun NetworkAsteroidContainer.asDataBaseModel(): Array<DatabaseAsteroid>{
    return asteroids.get(0).nearEarthObjects.x20220327.map{
        DatabaseAsteroid(
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
    }.toTypedArray()
}

