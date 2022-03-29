package com.example.asteroidscanner.data.network


import com.example.asteroidscanner.data.database.DatabaseAsteroid
import com.example.asteroidscanner.domain.Asteroid


// DomainModel and DatabaseModels mapping functions
fun GetAsteroidResponse.asDomainModel():List<Asteroid>{
    return nearEarthObjects.x20220327.map{
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
    return nearEarthObjects.x20220327.map{
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

