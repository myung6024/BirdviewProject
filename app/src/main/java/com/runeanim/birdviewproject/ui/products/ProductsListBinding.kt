package com.runeanim.birdviewproject.ui.products

import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.runeanim.birdviewproject.R
import com.runeanim.birdviewproject.model.Product
import com.runeanim.birdviewproject.util.HidingScrollListener


@BindingAdapter("app:items")
fun setItems(listView: RecyclerView, items: PagedList<Product>?) {
    items?.let {
        (listView.adapter as ProductsAdapter).submitList(items)
    }
}

@BindingAdapter("app:thumbnailImage")
fun setProductImage(imageView: ImageView, url: String?) {
    url?.let {
        Glide.with(imageView.context)
            .load(url)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(imageView)
    }
}

@BindingAdapter("app:hidingScrollListener")
fun setHidingScrollListener(listView: RecyclerView, targetView: View) {
    listView.addOnScrollListener(object : HidingScrollListener() {
        override fun onHide() {
            targetView.animate().translationY((-targetView.height).toFloat()).interpolator =
                AccelerateInterpolator(2f)
        }

        override fun onShow() {
            targetView.animate().translationY(0f).setInterpolator(DecelerateInterpolator(2f))
        }
    })
}