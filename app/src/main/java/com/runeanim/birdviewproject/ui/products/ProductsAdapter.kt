package com.runeanim.birdviewproject.ui.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.runeanim.birdviewproject.data.Product
import com.runeanim.birdviewproject.databinding.LoadingItemBinding
import com.runeanim.birdviewproject.databinding.ProductItemBinding

class ProductsAdapter(private val viewModel: ProductsViewModel) :
    ListAdapter<Product?, RecyclerView.ViewHolder>(ProductDiffCallback()) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null)
            (holder as ViewHolder).bind(viewModel, item)
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position) == null) LIST_TYPE_LOADING else LIST_TYPE_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == LIST_TYPE_LOADING)
            LoadingViewHolder.from(parent)
        else
            ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: ProductsViewModel, item: Product) {

            binding.viewmodel = viewModel
            binding.product = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ProductItemBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }

    class LoadingViewHolder private constructor(binding: LoadingItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): LoadingViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = LoadingItemBinding.inflate(layoutInflater, parent, false)

                return LoadingViewHolder(binding)
            }
        }
    }

    companion object {
        const val LIST_TYPE_ITEM = 0
        const val LIST_TYPE_LOADING = 1
    }
}

class ProductDiffCallback : DiffUtil.ItemCallback<Product?>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }
}
