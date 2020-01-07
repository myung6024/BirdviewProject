package com.runeanim.birdviewproject.ui.products


import androidx.appcompat.widget.SearchView
import androidx.databinding.BindingAdapter

@BindingAdapter("app:searchViewClickable")
fun activeSearchView(searchView: SearchView, clickable: Boolean) {
    if (clickable) {
        searchView.setOnClickListener {
            searchView.isIconified = false
        }
    }
}