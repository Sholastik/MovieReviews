package com.vyacheslavivanov.moviereviews.api.dto.review

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReviewListRequest(
    @Json(name = "offset") val offset: Int
)
