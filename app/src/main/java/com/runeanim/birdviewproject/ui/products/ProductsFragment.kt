package com.runeanim.birdviewproject.ui.products

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.runeanim.birdviewproject.R
import com.runeanim.birdviewproject.databinding.ProductsFragmentBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ProductsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<ProductsViewModel> { viewModelFactory }

    private lateinit var viewDataBinding: ProductsFragmentBinding

    private lateinit var listAdapter: ProductsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = ProductsFragmentBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        setupListAdapter()
    }

    private fun setupListAdapter() {
        val viewModel = viewDataBinding.viewmodel
        if (viewModel != null) {
            val spacing = resources.getDimensionPixelSize(R.dimen.recycler_spacing) / 2
            listAdapter = ProductsAdapter(viewModel)
            viewDataBinding.productsList.apply {
                clipToPadding = false
                clipChildren = false
                addItemDecoration(object : RecyclerView.ItemDecoration() {
                    override fun getItemOffsets(
                        outRect: Rect,
                        view: View,
                        parent: RecyclerView,
                        state: RecyclerView.State
                    ) {
                        outRect.set(spacing, spacing, spacing, spacing)
                    }
                })
                adapter = listAdapter
            }
        } else {
            Log.w(javaClass.name, "ViewModel not initialized when attempting to set up adapter.")
        }
    }
}
