package com.vyacheslavivanov.moviereviews.service.dto.reviews

import com.squareup.moshi.Json

data class ReviewListRequest(
    @Json(name = "offset") val offset: Int
)
