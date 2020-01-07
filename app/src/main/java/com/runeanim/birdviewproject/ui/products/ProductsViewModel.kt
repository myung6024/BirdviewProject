package com.runeanim.birdviewproject.ui.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.runeanim.birdviewproject.model.Product
import com.runeanim.birdviewproject.remote.BirdViewService
import com.runeanim.birdviewproject.source.ProductDataSource
import javax.inject.Inject

class ProductsViewModel @Inject constructor(
    private val birdViewService: BirdViewService
) : ViewModel() {

    private var _items: LiveData<PagedList<Product>> =
        initializedPagedListBuilder(ProductsFilterType.OILY).build()
    val items: LiveData<PagedList<Product>> = _items

    private fun initializedPagedListBuilder(skinType: ProductsFilterType):
            LivePagedListBuilder<Int, Product> {

        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setInitialLoadSizeHint(20)
            .setEnablePlaceholders(false)
            .setPrefetchDistance(8)
            .build()

        val dataSourceFactory = object : DataSource.Factory<Int, Product>() {
            override fun create(): DataSource<Int, Product> {
                return ProductDataSource(viewModelScope, birdViewService, skinType)
            }
        }
        return LivePagedListBuilder<Int, Product>(dataSourceFactory, config)
    }

    fun changeType(){
        _items.value!!.dataSource.invalidate()
        _items = initializedPagedListBuilder(ProductsFilterType.DRY).build()
    }
}