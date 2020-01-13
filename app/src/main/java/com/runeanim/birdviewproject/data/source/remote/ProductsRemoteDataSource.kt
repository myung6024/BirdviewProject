package com.runeanim.birdviewproject.data.source.remote

import com.runeanim.birdviewproject.base.BaseResponse
import com.runeanim.birdviewproject.data.Product
import com.runeanim.birdviewproject.data.ProductDetail
import com.runeanim.birdviewproject.data.source.ProductsDataSource
import com.runeanim.birdviewproject.ui.products.SkinFilterType
import io.reactivex.Single

class ProductsRemoteDataSource internal constructor(
    private val birdViewService: BirdViewService
) : ProductsDataSource {

    override fun getProducts(
        pageNum: Int,
        skinType: SkinFilterType?,
        searchKeyWord: String?
    ): Single<BaseResponse<List<Product>>> =
        birdViewService.getProducts(pageNum, skinType, searchKeyWord)

    override fun getProductDetail(productId: Int): Single<BaseResponse<ProductDetail>> =
        birdViewService.getProductDetail(productId)

}
