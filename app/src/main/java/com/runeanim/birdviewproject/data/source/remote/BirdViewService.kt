package com.runeanim.birdviewproject.data.source.remote

import com.runeanim.birdviewproject.base.BaseResponse
import com.runeanim.birdviewproject.data.Product
import com.runeanim.birdviewproject.data.ProductDetail
import com.runeanim.birdviewproject.ui.products.SkinFilterType
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BirdViewService {

    @GET("products")
    fun getProducts(
        @Query("page") pageNum: Int,
        @Query("skin_type") skinType: SkinFilterType? = null,
        @Query("search") searchKeyWord: String? = null
    ): Single<BaseResponse<List<Product>>>

    @GET("products/{productId}")
    fun getProductDetail(
        @Path("productId") repoUrl: Int
    ): Single<BaseResponse<ProductDetail>>
}
