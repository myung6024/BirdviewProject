package com.runeanim.birdviewproject.model

data class ProductsResponse(
    val statusCode: Int,
    val body: List<Product>
)