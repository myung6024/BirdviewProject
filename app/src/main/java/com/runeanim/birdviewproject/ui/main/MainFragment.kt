package com.runeanim.birdviewproject.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.runeanim.birdviewproject.R
import com.runeanim.birdviewproject.ui.products.ProductsFragment
import com.runeanim.birdviewproject.ui.products.ProductsViewModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = ProductsFragment()
    }

    private lateinit var viewModel: ProductsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.products_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ProductsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
