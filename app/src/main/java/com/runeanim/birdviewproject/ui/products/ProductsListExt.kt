package com.runeanim.birdviewproject.ui.products

import com.runeanim.birdviewproject.data.Product

fun MutableList<Product?>.addData(newProductList: List<Product>): MutableList<Product?> = apply {
    removeLoading()
    addAll(newProductList)
    add(null)
}.toMutableList()

fun MutableList<Product?>.clearData(): MutableList<Product?> = apply {
    clear()
    add(null)
}.toMutableList()

fun MutableList<Product?>.removeLoading(): MutableList<Product?> = apply {
    if (size != 0)
        this.removeAt(lastIndex)
}.toMutableList()
