package com.vyacheslavivanov.moviereviews.ui.review.adapter

import androidx.recyclerview.widget.RecyclerView
import com.vyacheslavivanov.moviereviews.data.review.Review
import com.vyacheslavivanov.moviereviews.databinding.ViewHolderReviewBinding

class ReviewViewHolder(
    private val binding: ViewHolderReviewBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(review: Review) {
        binding.review = review
        binding.executePendingBindings()
    }
}
