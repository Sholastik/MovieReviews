package com.vyacheslavivanov.moviereviews.api.source.reviews

import com.vyacheslavivanov.moviereviews.api.dto.reviews.ReviewListRequest
import com.vyacheslavivanov.moviereviews.api.dto.reviews.ReviewListResponse

abstract class ReviewsSource {
    abstract suspend fun getReviewList(reviewListRequest: ReviewListRequest): Result<ReviewListResponse>
}
