package com.vyacheslavivanov.moviereviews.data.reviews

data class Review(
    val title: String,
    val ageRating: String,
    val isCriticsPick: Boolean,
    val author: String,
    val headline: String,
    val summary: String,
    val dateCreated: String,
    val linkUrl: String,
    val thumbnailUrl: String
)
