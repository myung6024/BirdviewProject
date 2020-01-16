package com.runeanim.birdviewproject

import com.runeanim.birdviewproject.base.BaseResponse
import com.runeanim.birdviewproject.data.model.Product
import com.runeanim.birdviewproject.data.model.ProductDetail
import com.runeanim.birdviewproject.data.source.ProductsRepository
import com.runeanim.birdviewproject.ui.products.SkinFilterType
import io.reactivex.Observable
import io.reactivex.Single

class FakeRepository : ProductsRepository {
    private lateinit var productList: List<Product>
    private var statusCode = 200

    fun setProducts(list: List<Product>) {
        productList = list
    }

    fun setErrorState(code: Int) {
        statusCode = code
    }

    override fun getProducts(
        pageNum: Int,
        skinType: SkinFilterType?,
        searchKeyWord: String?
    ): Single<BaseResponse<List<Product>>> {
        return Observable.fromIterable(productList)
            .doOnNext {
                if (statusCode != 200) {
                    throw Throwable(statusCode.toString())
                }
            }
            .filter {
                if (searchKeyWord == null)
                    return@filter true
                return@filter it.title.contains(searchKeyWord)
            }
            .filter {
                if (skinType == null)
                    return@filter true
                when (skinType) {
                    SkinFilterType.OILY -> return@filter it.oilyScore > 70
                    SkinFilterType.DRY -> return@filter it.dryScore > 70
                    SkinFilterType.SENSITIVE -> return@filter it.sensitiveScore > 70
                }
            }
            .toList()
            .flatMap {
                Single.just(BaseResponse(statusCode, it))
            }
    }

    override fun getProductDetail(productId: Int): Single<BaseResponse<ProductDetail>> =
        Single.just(
            BaseResponse(
                statusCode,
                ProductDetail(
                    productId,
                    "10000",
                    "",
                    "test1",
                    "testtesttest",
                    0,
                    0,
                    0
                )
            )
        ).doOnSuccess {
            if (statusCode != 200) {
                throw Throwable(statusCode.toString())
            }
        }
}