package com.runeanim.birdviewproject.model

import com.google.gson.annotations.SerializedName

data class Product(
    val id: Int,
    val price: String,
    @SerializedName("thumbnail_image") val thumbnailImage: String,
    val title: String,
    @SerializedName("oily_score") val oilyScore: Int,
    @SerializedName("dry_score") val dryScore: Int,
    @SerializedName("sensitive_score") val sensitiveScore: Int
)