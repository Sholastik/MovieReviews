package com.vyacheslavivanov.moviereviews.api.source.review

import com.vyacheslavivanov.moviereviews.api.dto.review.ReviewListRequest
import com.vyacheslavivanov.moviereviews.api.dto.review.ReviewListResponse

abstract class ReviewSource {
    abstract suspend fun fetchReviewList(reviewListRequest: ReviewListRequest): Result<ReviewListResponse>
}
