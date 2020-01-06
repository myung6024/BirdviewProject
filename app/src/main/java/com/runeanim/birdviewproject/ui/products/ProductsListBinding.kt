package com.runeanim.birdviewproject.ui.products

import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.runeanim.birdviewproject.R
import com.runeanim.birdviewproject.data.Product
import com.runeanim.birdviewproject.util.HidingScrollListener
import java.text.DecimalFormat


@BindingAdapter("app:items")
fun setItems(listView: RecyclerView, items: List<Product>?) {
    items?.let {
        (listView.adapter as ProductsAdapter).submitList(it)
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

@BindingAdapter(value = ["app:format", "app:price"])
fun setPriceText(textView: TextView, formatter: DecimalFormat, price: String) {
    textView.text =
        textView.context.getString(R.string.price_format, formatter.format(price.toInt()))
}

@BindingAdapter("app:hidingScrollListener")
fun setHidingScrollListener(listView: RecyclerView, targetView: View) {
    listView.addOnScrollListener(object : HidingScrollListener() {
        override fun onHide() {
            targetView.animate().translationY((-targetView.height).toFloat()).interpolator =
                AccelerateInterpolator(2f)
        }

        override fun onShow() {
            targetView.animate().translationY(0f).interpolator = DecelerateInterpolator(2f)
        }
    })
}

@BindingAdapter("app:loadMoreListener")
fun setLoadMoreListener(listView: RecyclerView, loadMore: () -> Unit) {
    listView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            if (!listView.canScrollVertically(1)) {
                loadMore()
            }
        }
    })
}