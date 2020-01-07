package com.runeanim.birdviewproject.ui.products

import com.google.gson.annotations.SerializedName

enum class ProductsFilterType {
    @SerializedName("oily")
    OILY,
    @SerializedName("dry")
    DRY,
    @SerializedName("sensitive")
    SENSITIVE
}