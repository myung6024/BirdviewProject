package com.runeanim.birdviewproject.remote

import com.runeanim.birdviewproject.model.ProductsResponse
import com.runeanim.birdviewproject.ui.products.ProductsFilterType
import retrofit2.http.GET
import retrofit2.http.Query

interface BirdViewService {

    @GET("products")
    suspend fun getProducts(@Query("page") pageNum: Int, @Query("skin_type") skinType: ProductsFilterType): ProductsResponse
}