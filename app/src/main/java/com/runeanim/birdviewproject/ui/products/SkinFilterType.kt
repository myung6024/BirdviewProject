package com.runeanim.birdviewproject.ui.products

import androidx.annotation.StringRes
import com.google.gson.annotations.SerializedName
import com.runeanim.birdviewproject.R

enum class SkinFilterType(@StringRes val resId: Int) {
    @SerializedName("oily")
    OILY(R.string.skin_type_oily),
    @SerializedName("dry")
    DRY(R.string.skin_type_dry),
    @SerializedName("sensitive")
    SENSITIVE(R.string.skin_type_sensitive);

    fun getStringResId(): Int {
        return resId
    }
}
