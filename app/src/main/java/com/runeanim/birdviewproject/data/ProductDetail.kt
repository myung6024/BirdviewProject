package com.runeanim.birdviewproject.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProductDetail(
    val id: Int,
    val price: String,
    @SerializedName("full_size_image") val imageUrl: String,
    val title: String,
    @SerializedName("description") private val _description: String,
    @SerializedName("oily_score") @Expose val oilyScore: Int,
    @SerializedName("dry_score") @Expose val dryScore: Int,
    @SerializedName("sensitive_score") @Expose val sensitiveScore: Int
) {
    fun getDescription(): String =
        _description.replace("\\n", "\n")
}