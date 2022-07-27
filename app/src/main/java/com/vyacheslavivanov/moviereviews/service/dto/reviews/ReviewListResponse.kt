package com.vyacheslavivanov.moviereviews.service.dto.reviews

import com.squareup.moshi.Json

data class ReviewListResponse(
    @Json(name = "has_more") val hasMore: Boolean,
    @Json(name = "results") val reviewList: List<ReviewResponse>
)
