package com.runeanim.birdviewproject.source

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.ItemKeyedDataSource
import com.runeanim.birdviewproject.model.Product
import com.runeanim.birdviewproject.remote.BirdViewService
import com.runeanim.birdviewproject.ui.products.ProductsFilterType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ProductDataSource(
    private val scope: CoroutineScope,
    private val birdViewService: BirdViewService,
    private val skinType: ProductsFilterType
) : ItemKeyedDataSource<Int, Product>() {

    val networkErrors: MutableLiveData<String> = MutableLiveData()
    private var initKey = 1

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Product>
    ) {
        scope.launch {
            try {
                val response = birdViewService.getProducts(initKey++, skinType)
                callback.onResult(response.body)
            }catch (exception : Exception){
                Log.e("PostsDataSource", "Failed to fetch data!")
            }

        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Product>) {
        Log.d("PostsDataSource", "key : $initKey")
        scope.launch {
            try {
                val response = birdViewService.getProducts(initKey++, skinType)
                callback.onResult(response.body)
            }catch (exception : Exception){
                Log.e("PostsDataSource", "Failed to fetch data!")
            }

        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Product>) {
    }

    override fun getKey(item: Product): Int {
        return initKey
    }

    override fun invalidate() {
        super.invalidate()
    }
}