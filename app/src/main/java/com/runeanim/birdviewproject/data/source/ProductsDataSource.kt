package com.runeanim.birdviewproject.data.source

import com.runeanim.birdviewproject.base.BaseResponse
import com.runeanim.birdviewproject.data.model.Product
import com.runeanim.birdviewproject.data.model.ProductDetail
import com.runeanim.birdviewproject.ui.products.SkinFilterType
import io.reactivex.Single

interface ProductsDataSource {

    fun getProducts(
        pageNum: Int,
        skinType: SkinFilterType? = null,
        searchKeyWord: String? = null
    ): Single<BaseResponse<List<Product>>>

    fun getProductDetail(
        productId: Int
    ): Single<BaseResponse<ProductDetail>>
}
