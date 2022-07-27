package com.vyacheslavivanov.moviereviews.api.dto.review

import com.squareup.moshi.Json

data class ReviewListRequest(
    @Json(name = "offset") val offset: Int
)
