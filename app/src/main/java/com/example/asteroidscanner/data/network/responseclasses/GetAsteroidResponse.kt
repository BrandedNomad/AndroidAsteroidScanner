package com.example.asteroidscanner.data.network.responseclasses


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetAsteroidResponse(

    @Json(name = "links")
    val links: Links = Links(),
    @Json(name = "element_count")
    val elementCount: Int = 0,
    @Json(name = "near_earth_objects")
    val nearEarthObjects: Map<String,List<AsteroidItem>>
) {

    @JsonClass(generateAdapter = true)
    data class Links(
        @Json(name = "next")
        val next: String = "",
        @Json(name = "prev")
        val prev: String = "",
        @Json(name = "self")
        val self: String = ""
    )

    @JsonClass(generateAdapter = true)
    data class AsteroidItem(
        @Json(name = "links")
        val links: Links = Links(),
        @Json(name = "id")
        val id: String = "",
        @Json(name = "neo_reference_id")
        val neoReferenceId: String = "",
        @Json(name = "name")
        val name: String = "",
        @Json(name = "nasa_jpl_url")
        val nasaJplUrl: String = "",
        @Json(name = "absolute_magnitude_h")
        val absoluteMagnitudeH: Double = 0.0,
        @Json(name = "estimated_diameter")
        val estimatedDiameter: EstimatedDiameter = EstimatedDiameter(),
        @Json(name = "is_potentially_hazardous_asteroid")
        val isPotentiallyHazardousAsteroid: Boolean = false,
        @Json(name = "close_approach_data")
        val closeApproachData: List<CloseApproachData> = listOf(),
        @Json(name = "is_sentry_object")
        val isSentryObject: Boolean = false
    ) {
        @JsonClass(generateAdapter = true)
        data class Links(
            @Json(name = "self")
            val self: String = ""
        )

        @JsonClass(generateAdapter = true)
        data class EstimatedDiameter(
            @Json(name = "kilometers")
            val kilometers: Kilometers = Kilometers(),
            @Json(name = "meters")
            val meters: Meters = Meters(),
            @Json(name = "miles")
            val miles: Miles = Miles(),
            @Json(name = "feet")
            val feet: Feet = Feet()
        ) {
            @JsonClass(generateAdapter = true)
            data class Kilometers(
                @Json(name = "estimated_diameter_min")
                val estimatedDiameterMin: Double = 0.0,
                @Json(name = "estimated_diameter_max")
                val estimatedDiameterMax: Double = 0.0
            )

            @JsonClass(generateAdapter = true)
            data class Meters(
                @Json(name = "estimated_diameter_min")
                val estimatedDiameterMin: Double = 0.0,
                @Json(name = "estimated_diameter_max")
                val estimatedDiameterMax: Double = 0.0
            )

            @JsonClass(generateAdapter = true)
            data class Miles(
                @Json(name = "estimated_diameter_min")
                val estimatedDiameterMin: Double = 0.0,
                @Json(name = "estimated_diameter_max")
                val estimatedDiameterMax: Double = 0.0
            )

            @JsonClass(generateAdapter = true)
            data class Feet(
                @Json(name = "estimated_diameter_min")
                val estimatedDiameterMin: Double = 0.0,
                @Json(name = "estimated_diameter_max")
                val estimatedDiameterMax: Double = 0.0
            )
        }

        @JsonClass(generateAdapter = true)
        data class CloseApproachData(
            @Json(name = "close_approach_date")
            val closeApproachDate: String = "",
            @Json(name = "close_approach_date_full")
            val closeApproachDateFull: String = "",
            @Json(name = "epoch_date_close_approach")
            val epochDateCloseApproach: Long = 0,
            @Json(name = "relative_velocity")
            val relativeVelocity: RelativeVelocity = RelativeVelocity(),
            @Json(name = "miss_distance")
            val missDistance: MissDistance = MissDistance(),
            @Json(name = "orbiting_body")
            val orbitingBody: String = ""
        ) {
            @JsonClass(generateAdapter = true)
            data class RelativeVelocity(
                @Json(name = "kilometers_per_second")
                val kilometersPerSecond: String = "",
                @Json(name = "kilometers_per_hour")
                val kilometersPerHour: String = "",
                @Json(name = "miles_per_hour")
                val milesPerHour: String = ""
            )

            @JsonClass(generateAdapter = true)
            data class MissDistance(
                @Json(name = "astronomical")
                val astronomical: String = "",
                @Json(name = "lunar")
                val lunar: String = "",
                @Json(name = "kilometers")
                val kilometers: String = "",
                @Json(name = "miles")
                val miles: String = ""
            )
        }
    }


}