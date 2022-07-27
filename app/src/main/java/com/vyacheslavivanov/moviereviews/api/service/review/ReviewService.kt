package com.vyacheslavivanov.moviereviews.api.service.review

import com.vyacheslavivanov.moviereviews.api.dto.review.ReviewListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ReviewService {
    @GET("/reviews/all.json")
    suspend fun fetchReviewList(@QueryMap queryMap: Map<String, Any>): Response<ReviewListResponse>
}
