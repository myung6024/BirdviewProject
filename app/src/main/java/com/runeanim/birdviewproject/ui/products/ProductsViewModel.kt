package com.runeanim.birdviewproject.ui.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.runeanim.birdviewproject.remote.BirdViewService
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductsViewModel @Inject constructor(
    private val birdViewService: BirdViewService
) : ViewModel() {
    init {
        viewModelScope.launch {
            val result = birdViewService.searchRepositories()
        }
    }
}