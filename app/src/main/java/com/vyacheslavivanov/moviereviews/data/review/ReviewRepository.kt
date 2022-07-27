package com.vyacheslavivanov.moviereviews.data.review

import com.vyacheslavivanov.moviereviews.api.source.review.ReviewPagingSource
import com.vyacheslavivanov.moviereviews.api.source.review.ReviewSource
import javax.inject.Inject

class ReviewRepository @Inject constructor(
    private val reviewSource: ReviewSource
) {
    fun createPagingSource(): ReviewPagingSource = ReviewPagingSource(reviewSource)
}
