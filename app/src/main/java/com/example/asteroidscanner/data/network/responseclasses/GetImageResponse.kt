package com.example.asteroidscanner.data.network.responseclasses


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class ImageContainer(
    val image:List<GetImageResponse>
)

@JsonClass(generateAdapter = true)
data class GetImageResponse(
    @Json(name = "date")
    val date: String = "",
    @Json(name = "explanation")
    val explanation: String = "",
    @Json(name = "media_type")
    val mediaType: String = "",
    @Json(name = "service_version")
    val serviceVersion: String = "",
    @Json(name = "title")
    val title: String = "",
    @Json(name = "url")
    val url: String = ""
)