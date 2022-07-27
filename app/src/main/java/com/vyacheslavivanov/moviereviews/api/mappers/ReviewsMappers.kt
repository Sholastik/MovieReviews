package com.vyacheslavivanov.moviereviews.api.mappers

import com.vyacheslavivanov.moviereviews.api.dto.reviews.ReviewListRequest
import com.vyacheslavivanov.moviereviews.api.dto.reviews.ReviewListResponse
import com.vyacheslavivanov.moviereviews.api.dto.reviews.ReviewResponse
import com.vyacheslavivanov.moviereviews.data.reviews.Review
import com.vyacheslavivanov.moviereviews.data.reviews.ReviewList

fun ReviewListResponse.toDomain(): ReviewList =
    ReviewList(
        mapList(reviewList) {
            it.toDomain()
        },
        hasMore
    )

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

fun ReviewListRequest.toQueryMap(): Map<String, Any> =
    mapOf(
        "offset" to offset
    )
