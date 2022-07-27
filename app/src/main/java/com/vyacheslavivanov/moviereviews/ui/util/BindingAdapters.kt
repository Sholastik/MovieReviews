package com.vyacheslavivanov.moviereviews.ui.util

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation


@BindingAdapter("imageUrl")
fun bindImageUrl(view: ImageView, imageUrl: String) {
    Picasso.get()
        .load(imageUrl)
        .fit()
        .centerCrop()
        .transform(RoundedCornersTransformation(16, 8))
        .into(view)
}

@BindingAdapter("openUrlOnClick")
fun bindOpenUrlOnClick(view: View, url: String) {
    view.setOnClickListener {
        it.context.startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(url)
            )
        )
    }
}
