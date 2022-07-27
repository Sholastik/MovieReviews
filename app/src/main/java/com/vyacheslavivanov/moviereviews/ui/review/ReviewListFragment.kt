package com.vyacheslavivanov.moviereviews.ui.review

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
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

        val adapter = ReviewAdapter()

        binding.bindAdapter(adapter)

        viewLifecycleOwner.run {
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.reviewFlow.collectLatest {
                        adapter.submitData(it)
                    }
                }
            }
        }

        return binding.root
    }

    companion object {
        private fun FragmentReviewListBinding.bindAdapter(
            reviewAdapter: ReviewAdapter
        ) {
            recyclerView.apply {
                adapter = reviewAdapter
                layoutManager = LinearLayoutManager(context)
            }
        }
    }
}
