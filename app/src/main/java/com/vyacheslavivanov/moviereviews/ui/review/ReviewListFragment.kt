package com.vyacheslavivanov.moviereviews.ui.review

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.vyacheslavivanov.moviereviews.R
import com.vyacheslavivanov.moviereviews.databinding.FragmentReviewListBinding
import com.vyacheslavivanov.moviereviews.ui.review.adapter.ReviewAdapter
import com.vyacheslavivanov.moviereviews.viewmodel.review.ReviewListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ReviewListFragment : Fragment() {
    private lateinit var binding: FragmentReviewListBinding
    private val viewModel: ReviewListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReviewListBinding.inflate(layoutInflater, container, false)
        binding.bindAdapter(ReviewAdapter())

        return binding.root
    }

    private fun showErrorSnackbar(error: Throwable, onClick: () -> Unit) {
        Snackbar.make(
            binding.root,
            error.toString(),
            Snackbar.LENGTH_INDEFINITE
        ).setAction(R.string.error_snackbar_title) {
            onClick()
        }.show()
    }

    private fun getError(states: CombinedLoadStates): LoadState.Error? {
        return states.source.append as? LoadState.Error
            ?: states.source.prepend as? LoadState.Error
            ?: states.append as? LoadState.Error
            ?: states.prepend as? LoadState.Error
    }

    private fun FragmentReviewListBinding.bindAdapter(
        reviewAdapter: ReviewAdapter
    ) {
        viewLifecycleOwner.run {
            lifecycleScope.launch {
                // May be paused, but still visible (multi window app)
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.reviewFlow.collectLatest {
                        reviewAdapter.submitData(it)
                    }
                }
            }

            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    reviewAdapter.loadStateFlow.collectLatest {
                        // Show progress bar on initial load

                        val isListEmpty =
                            it.refresh is LoadState.NotLoading && reviewAdapter.itemCount == 0
                        binding.progressBar.isVisible = isListEmpty

                        binding.appendProgress.isVisible = it.append is LoadState.Loading

                        getError(it)?.let { errorState ->
                            showErrorSnackbar(errorState.error) {
                                reviewAdapter.retry()
                            }
                        }
                    }
                }
            }
        }

        recyclerView.apply {
            adapter = reviewAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }
}
