package com.example.asteroidscanner.data.database

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.asteroidscanner.domain.Asteroid

//Entity
@Entity
data class DatabaseAsteroid constructor(
    @PrimaryKey
    val id:String,
    val name:String,
    val absolute_magnitude:Float,
    val close_approach_date: String,
    val relative_velocity: String,
    val estimated_diameter: Float,
    val distance_from_earth: String,
    val status: Boolean
)

//An extension function which converts from a database oject ot domain object
fun List<DatabaseAsteroid>.asDomainModel():List<Asteroid>{
    return map{
        Asteroid(
            id = it.id,
            name = it.name,
            absolute_magnitude = it.absolute_magnitude,
            close_approach_date = it.close_approach_date,
            relative_velocity = it.relative_velocity,
            estimated_diameter = it.estimated_diameter,
            distance_from_earth = it.distance_from_earth,
            status = it.status
        )
    }
}