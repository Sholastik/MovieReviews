package com.vyacheslavivanov.moviereviews.ui.reviews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vyacheslavivanov.moviereviews.databinding.FragmentReviewListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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