package com.runeanim.birdviewproject.data.source

import com.runeanim.birdviewproject.base.BaseResponse
import com.runeanim.birdviewproject.data.Product
import com.runeanim.birdviewproject.data.ProductDetail
import com.runeanim.birdviewproject.ui.products.SkinFilterType
import io.reactivex.Single

interface ProductsRepository {

    fun getProducts(
        pageNum: Int,
        skinType: SkinFilterType? = null,
        searchKeyWord: String? = null
    ): Single<BaseResponse<List<Product>>>

    fun getProductDetail(
        productId: Int
    ): Single<BaseResponse<ProductDetail>>
}
