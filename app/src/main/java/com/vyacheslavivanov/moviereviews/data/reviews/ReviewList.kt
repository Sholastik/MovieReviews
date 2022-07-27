package com.vyacheslavivanov.moviereviews.data.reviews

data class ReviewList(
    val reviewList: List<Review>,
    val hasNext: Boolean
)