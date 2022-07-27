package com.vyacheslavivanov.moviereviews.api.source.reviews.paging

import androidx.paging.PagingState
import com.vyacheslavivanov.moviereviews.api.dto.reviews.ReviewListRequest
import com.vyacheslavivanov.moviereviews.api.mappers.toDomain
import com.vyacheslavivanov.moviereviews.api.source.reviews.ReviewsSource
import com.vyacheslavivanov.moviereviews.api.util.foldLogging
import com.vyacheslavivanov.moviereviews.data.reviews.Review
import javax.inject.Inject

class ReviewPagingSourceImpl @Inject constructor(
    private val reviewsSource: ReviewsSource
) : ReviewPagingSource() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Review> {
        val offset = params.key ?: STARTING_KEY

        val result = reviewsSource.fetchReviewList(
            ReviewListRequest(offset = offset)
        ).foldLogging {
            toDomain()
        }

        return result.fold(
            onSuccess = {
                LoadResult.Page(
                    data = it.reviewList,
                    prevKey = when (offset) {
                        STARTING_KEY -> null
                        else -> offset - PAGE_SIZE
                    },
                    nextKey = when (it.hasNext) {
                        true -> offset + PAGE_SIZE
                        false -> null
                    }
                )
            },
            onFailure = {
                LoadResult.Error(it)
            }
        )
    }

    override fun getRefreshKey(state: PagingState<Int, Review>): Int = STARTING_KEY

    companion object {
        private const val STARTING_KEY: Int = 0
        private const val PAGE_SIZE: Int = 20
    }
}