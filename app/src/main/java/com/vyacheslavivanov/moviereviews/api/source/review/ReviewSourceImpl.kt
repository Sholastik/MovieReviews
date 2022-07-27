package com.vyacheslavivanov.moviereviews.api.source.review

import com.vyacheslavivanov.moviereviews.api.dto.review.ReviewListRequest
import com.vyacheslavivanov.moviereviews.api.dto.review.ReviewListResponse
import com.vyacheslavivanov.moviereviews.api.mappers.toQueryMap
import com.vyacheslavivanov.moviereviews.api.service.review.ReviewService
import com.vyacheslavivanov.moviereviews.api.util.fold
import com.vyacheslavivanov.moviereviews.di.ReviewApiModule
import javax.inject.Inject

class ReviewSourceImpl @Inject constructor(
    @ReviewApiModule.ReviewApi private val reviewService: ReviewService
) : ReviewSource() {
    override suspend fun fetchReviewList(reviewListRequest: ReviewListRequest): Result<ReviewListResponse> =
        reviewService.fetchReviewList(reviewListRequest.toQueryMap()).fold()
}