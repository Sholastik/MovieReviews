package com.vyacheslavivanov.moviereviews.api.source.review.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.vyacheslavivanov.moviereviews.data.review.Review

abstract class ReviewPagingSource : PagingSource<Int, Review>() {
    abstract override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Review>

    abstract override fun getRefreshKey(state: PagingState<Int, Review>): Int
}
