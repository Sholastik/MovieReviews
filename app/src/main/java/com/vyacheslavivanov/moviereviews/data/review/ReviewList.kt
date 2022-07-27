package com.vyacheslavivanov.moviereviews.data.review

data class ReviewList(
    val reviewList: List<Review>,
    val hasNext: Boolean
)