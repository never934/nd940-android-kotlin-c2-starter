package com.udacity.asteroidradar.network.response

import com.squareup.moshi.Json

data class ImageOfDayResponse(
    @Json(name = "media_type")
    val mediaType: String,
    val title: String,
    val url: String
)