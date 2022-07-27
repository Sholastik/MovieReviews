package com.vyacheslavivanov.moviereviews.api.mappers

import com.vyacheslavivanov.moviereviews.api.dto.reviews.ReviewListResponse
import com.vyacheslavivanov.moviereviews.api.dto.reviews.ReviewResponse
import com.vyacheslavivanov.moviereviews.data.reviews.Review

fun ReviewListResponse.toDomain(): List<Review> = mapList(reviewList) {
    it.toDomain()
}

fun ReviewResponse.toDomain(): Review =
    Review(
        title = title,
        ageRating = ageRating,
        isCriticsPick = isCriticsPick == 1,
        author = author,
        headline = headline,
        summary = summary,
        dateCreated = dateCreated,
        linkUrl = link.url,
        thumbnailUrl = image.src
    )
