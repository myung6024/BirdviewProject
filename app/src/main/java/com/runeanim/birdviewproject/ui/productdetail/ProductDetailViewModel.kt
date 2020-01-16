package com.runeanim.birdviewproject.ui.productdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.runeanim.birdviewproject.base.BaseViewModel
import com.runeanim.birdviewproject.data.model.ProductDetail
import com.runeanim.birdviewproject.data.source.ProductsRepository
import com.runeanim.birdviewproject.util.getSnackbarMessage
import java.text.DecimalFormat
import javax.inject.Inject

class ProductDetailViewModel @Inject constructor(
    private val productsRepository: ProductsRepository
) : BaseViewModel() {

    @Inject
    lateinit var formatter: DecimalFormat

    private val _product = MutableLiveData<ProductDetail>()

    val product: LiveData<ProductDetail?> = _product

    fun start(productId: Int?) {
        if (productId == null)
            return

        getProductDetail(productId)
    }

    private fun getProductDetail(productId: Int) {
        compositeDisposable.add(
            productsRepository.getProductDetail(productId)
                .subscribe({
                    _product.value = it.body
                }, {
                    it.message?.let { statusCode ->
                        showSnackbarMessage(statusCode.getSnackbarMessage())
                    }
                })
        )
    }
}
