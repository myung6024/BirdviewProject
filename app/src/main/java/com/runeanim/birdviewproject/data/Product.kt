package com.runeanim.birdviewproject.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Product(
    val id: Int,
    val price: String,
    @SerializedName("thumbnail_image") val thumbnailImage: String,
    val title: String,
    @SerializedName("oily_score") @Expose val oilyScore: Int,
    @SerializedName("dry_score") @Expose val dryScore: Int,
    @SerializedName("sensitive_score") @Expose val sensitiveScore: Int
)
