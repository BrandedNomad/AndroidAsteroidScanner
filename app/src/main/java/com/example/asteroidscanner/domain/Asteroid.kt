package com.example.asteroidscanner.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Asteroid(
    val id:String,
    val name:String,
    val date:String,
    val absolute_magnitude:Double,
    val estimated_diameter: Double,
    val close_approach_date: String,
    val relative_velocity: String,
    val distance_from_earth: String,
    val status: Boolean
) : Parcelable

