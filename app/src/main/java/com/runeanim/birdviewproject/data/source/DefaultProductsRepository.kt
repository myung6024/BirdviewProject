package com.runeanim.birdviewproject.data.source

import com.runeanim.birdviewproject.base.BaseResponse
import com.runeanim.birdviewproject.data.Product
import com.runeanim.birdviewproject.di.ApplicationModule.ProductsRemoteDataSource
import com.runeanim.birdviewproject.ui.products.SkinFilterType
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DefaultProductsRepository @Inject constructor(
    @ProductsRemoteDataSource private val productsRemoteDataSource: ProductsDataSource
) : ProductsRepository {

    override fun getProducts(
        pageNum: Int,
        skinType: SkinFilterType?,
        searchKeyWord: String?
    ): Single<BaseResponse<List<Product>>> =
        productsRemoteDataSource.getProducts(pageNum, skinType, searchKeyWord)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}
