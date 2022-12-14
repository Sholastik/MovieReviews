package com.vyacheslavivanov.moviereviews.viewmodel.review

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.vyacheslavivanov.moviereviews.data.review.ReviewRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReviewListViewModel @Inject constructor(
    reviewRepository: ReviewRepository
) : ViewModel() {
    val reviewFlow = Pager(
        config = PagingConfig(
            pageSize = PAGE_SIZE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            reviewRepository.createPagingSource()
        }
    )
        .flow
        .cachedIn(viewModelScope)

    companion object {
        private const val PAGE_SIZE = 25
    }
}
