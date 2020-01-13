package com.runeanim.birdviewproject.ui.productdetail

import android.graphics.Outline
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewOutlineProvider
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.facebook.shimmer.ShimmerFrameLayout
import com.runeanim.birdviewproject.R

@BindingAdapter(value = ["app:imageUrl", "app:shimmerLayout"])
fun setShimmerImage(imageView: ImageView, url: String?, shimmerLayout: ShimmerFrameLayout) {
    Glide.with(imageView.context)
        .load(url)
        .placeholder(R.drawable.ic_launcher_foreground)
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                shimmerLayout.hideShimmer()
                return false
            }
        })
        .into(imageView)
}

@BindingAdapter(value = ["app:text", "app:shimmerView", "app:shimmerLayout"])
fun setShimmerText(
    textView: TextView,
    text: String?,
    shimmerView: View,
    shimmerLayout: ShimmerFrameLayout
) {
    if (!text.isNullOrEmpty()) {
        textView.text = text
        shimmerView.visibility = View.GONE
        shimmerLayout.hideShimmer()
    }
}

@BindingAdapter("app:topRadius")
fun setScrollViewTopRadius(scrollView: ScrollView, radiusSize: Float) {
    scrollView.outlineProvider = object : ViewOutlineProvider() {
        override fun getOutline(view: View, outline: Outline) {
            outline.setRoundRect(
                0,
                0,
                view.width,
                view.height,
                radiusSize
            )
        }
    }
    scrollView.clipToOutline = true
}
