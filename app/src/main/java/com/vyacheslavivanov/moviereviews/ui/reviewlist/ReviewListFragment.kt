package com.vyacheslavivanov.moviereviews.ui.reviewlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vyacheslavivanov.moviereviews.databinding.FragmentReviewListBinding

class ReviewListFragment : Fragment() {
    private lateinit var binding: FragmentReviewListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReviewListBinding.inflate(layoutInflater, container, false)

        return binding.root
    }
}