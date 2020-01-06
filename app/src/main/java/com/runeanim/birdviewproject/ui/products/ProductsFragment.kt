package com.runeanim.birdviewproject.ui.products

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.runeanim.birdviewproject.R
import com.runeanim.birdviewproject.databinding.ProductsFragmentBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ProductsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<ProductsViewModel> { viewModelFactory }

    private lateinit var viewDataBinding: ProductsFragmentBinding

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
        setupRecyclerView()
        setupSnackBar()
        setupFilterButton()
        setupSearchView()
    }

    private fun setupSnackBar() {
        view?.let {
            viewModel.snackbarText.observe(viewLifecycleOwner, Observer { event ->
                event.getContentIfNotHandled()?.let { resId ->
                    Snackbar.make(it, resId, Snackbar.LENGTH_SHORT).show()
                }
            })
        }
    }

    private fun setupRecyclerView() {
        val viewModel = viewDataBinding.viewmodel
        if (viewModel != null) {
            viewDataBinding.productsList.apply {
                adapter = ProductsAdapter(viewModel).apply {
                    registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
                        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                            if (positionStart == 0)
                                layoutManager?.scrollToPosition(0)
                        }
                    })
                }

                layoutManager = GridLayoutManager(this.context, 2).apply {
                    spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                        override fun getSpanSize(position: Int): Int {
                            return if (adapter?.getItemViewType(position) == ProductsAdapter.LIST_TYPE_LOADING) 2 else 1
                        }
                    }
                }
            }
        } else {
            Log.w(javaClass.name, "ViewModel not initialized when attempting to set up adapter.")
        }
    }

    private fun setupFilterButton() {
        viewDataBinding.filterButton.setOnClickListener {
            createFilterSelectDialog().show()
        }
    }

    private fun setupSearchView() {
        viewDataBinding.searchView.apply {
            setOnClickListener {
                this.isIconified = false
            }
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText.isNullOrEmpty()) {
                        viewModel.searchData(null)
                    }
                    return false
                }

                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let { keyword ->
                        viewModel.searchData(keyword)
                    }
                    return false
                }
            })
        }
    }

    private fun createFilterSelectDialog(): AlertDialog =
        context?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(R.string.dialog_skin_type_title)
                .setItems(
                    R.array.skin_types_array
                ) { _, which ->
                    viewModel.changeType(
                        when (which) {
                            0 -> SkinFilterType.OILY
                            1 -> SkinFilterType.DRY
                            else -> SkinFilterType.SENSITIVE
                        }
                    )
                }
            return builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
}
