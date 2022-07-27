package com.vyacheslavivanov.moviereviews.api.dto.review

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReviewResponse(
    @Json(name = "display_title") val title: String,
    @Json(name = "mpaa_rating") val ageRating: String,
    @Json(name = "critics_pick") val isCriticsPick: Int,
    @Json(name = "byline") val author: String,
    @Json(name = "headline") val headline: String,
    @Json(name = "summary_short") val summary: String,
    @Json(name = "publication_date") val dateCreated: String,
    @Json(name = "opening_date") val openingDate: String?,
    @Json(name = "date_updated") val dateUpdated: String,
    @Json(name = "link") val link: Link,
    @Json(name = "multimedia") val image: Image
) {
    @JsonClass(generateAdapter = true)
    data class Link(
        @Json(name = "url") val url: String,
        @Json(name = "suggested_link_text") val linkText: String
    )

    @JsonClass(generateAdapter = true)
    data class Image(
        @Json(name = "src") val src: String
    )
}
