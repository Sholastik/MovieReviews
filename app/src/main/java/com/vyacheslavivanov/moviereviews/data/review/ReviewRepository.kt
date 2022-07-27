package com.vyacheslavivanov.moviereviews.data.review

import com.vyacheslavivanov.moviereviews.api.source.review.ReviewPagingSource
import com.vyacheslavivanov.moviereviews.api.source.review.ReviewSource
import com.vyacheslavivanov.moviereviews.di.ReviewApiModule
import javax.inject.Inject

class ReviewRepository @Inject constructor(
    @ReviewApiModule.ReviewApi private val reviewSource: ReviewSource
) {
    fun createPagingSource(): ReviewPagingSource = ReviewPagingSource(reviewSource)
}
