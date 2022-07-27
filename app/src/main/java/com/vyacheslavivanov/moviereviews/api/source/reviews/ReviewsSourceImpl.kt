package com.vyacheslavivanov.moviereviews.api.source.reviews

import com.vyacheslavivanov.moviereviews.api.dto.reviews.ReviewListRequest
import com.vyacheslavivanov.moviereviews.api.dto.reviews.ReviewListResponse
import com.vyacheslavivanov.moviereviews.api.mappers.toQueryMap
import com.vyacheslavivanov.moviereviews.api.service.reviews.ReviewsService
import com.vyacheslavivanov.moviereviews.api.util.fold
import javax.inject.Inject

class ReviewsSourceImpl @Inject constructor(
    private val reviewsService: ReviewsService
) : ReviewsSource() {
    override suspend fun getReviewList(reviewListRequest: ReviewListRequest): Result<ReviewListResponse> =
        reviewsService.fetchReviewList(reviewListRequest.toQueryMap()).fold()
}