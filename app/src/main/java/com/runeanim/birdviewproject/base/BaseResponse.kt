package com.runeanim.birdviewproject.base

data class BaseResponse<T>(
    val statusCode: Int,
    val body: T
)
