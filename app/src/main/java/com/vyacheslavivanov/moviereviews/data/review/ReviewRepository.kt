package com.vyacheslavivanov.moviereviews.data.review

import com.vyacheslavivanov.moviereviews.api.source.review.paging.ReviewPagingSource
import javax.inject.Inject

class ReviewRepository @Inject constructor(
    val pagingSource: ReviewPagingSource
)
