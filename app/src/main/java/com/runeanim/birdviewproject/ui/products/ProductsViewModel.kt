package com.runeanim.birdviewproject.ui.products

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.runeanim.birdviewproject.base.BaseViewModel
import com.runeanim.birdviewproject.data.model.Product
import com.runeanim.birdviewproject.data.source.ProductsRepository
import com.runeanim.birdviewproject.util.Event
import com.runeanim.birdviewproject.util.getSnackbarMessage
import java.text.DecimalFormat
import javax.inject.Inject

class ProductsViewModel @Inject constructor(
    private val productsRepository: ProductsRepository
) : BaseViewModel() {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val productList = mutableListOf<Product?>(null)

    private val _items = MutableLiveData<List<Product?>>(productList.toMutableList())
    val items: LiveData<List<Product?>> = _items

    private val _skinType = MutableLiveData<SkinFilterType>(SkinFilterType.OILY)
    val skinType: LiveData<SkinFilterType> = _skinType

    private var isLoading = false

    private var pageNum = 1

    private var searchKeyWord: String? = null

    @Inject
    lateinit var formatter: DecimalFormat

    private val _showProductDetailEvent = MutableLiveData<Event<Int>>()
    val showProductDetailEvent: LiveData<Event<Int>> = _showProductDetailEvent

    init {
        loadData()
    }

    val loadMore: () -> Unit = fun() {
        loadData()
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun loadData(forcedUpdate: Boolean = false) {
        if (forcedUpdate) {
            if (compositeDisposable.size() > 0) {
                compositeDisposable.clear()
            }
            isLoading = false
        }
        if (!isLoading) {
            isLoading = true
            compositeDisposable.add(
                productsRepository.getProducts(
                    pageNum++,
                    skinType.value,
                    searchKeyWord
                ).subscribe({
                    _items.value = productList.addData(it.body)
                    isLoading = false
                }, {
                    _items.value = productList.removeLoading()
                    it.message?.let { statusCode ->
                        showSnackbarMessage(statusCode.getSnackbarMessage(productList.isEmpty()))
                    }
                })
            )
        }
    }

    fun searchData(searchKeyWord: String?) {
        if (this.searchKeyWord.equals(searchKeyWord))
            return
        clearData()
        this.searchKeyWord = searchKeyWord
        loadData(true)
    }

    private fun clearData() {
        pageNum = 1
        _items.value = productList.clearData()
    }

    fun changeType(skinType: SkinFilterType) {
        if (_skinType.value == skinType)
            return

        _skinType.value = skinType
        clearData()
        loadData(true)
    }

    fun showProductDetail(productId: Int) {
        _showProductDetailEvent.value = Event(productId)
    }
}