package com.runeanim.birdviewproject.util

import com.runeanim.birdviewproject.R

fun String.getSnackbarMessage(onSearch: Boolean = false): Int {
    when (this) {
        "400" -> {
            return R.string.failed_load_data_400
        }
        "404" -> {
            return if (onSearch)
                R.string.failed_load_data_404_on_search
            else
                R.string.failed_load_data_404
        }
        "500" -> {
            return R.string.failed_load_data_500
        }
        "timeout" -> {
            return R.string.failed_load_data_timeout
        }
        else -> {
            return R.string.failed_load_data_unknown
        }
    }
}
