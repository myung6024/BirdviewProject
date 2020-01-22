package com.runeanim.birdviewproject.products

import com.google.common.truth.Truth
import com.runeanim.birdviewproject.data.model.Product
import com.runeanim.birdviewproject.ui.products.addData
import com.runeanim.birdviewproject.ui.products.clearData
import com.runeanim.birdviewproject.ui.products.removeLoading
import org.junit.Before
import org.junit.Test

class ProductsListExtTest {

    private lateinit var productList: MutableList<Product?>
    @Before
    fun setupViewModel() {
        productList = mutableListOf(null)
    }

    @Test
    fun `초기화 상태 (로딩 있음)`() {
        Truth.assertThat(productList).hasSize(1)
    }

    @Test
    fun `addData (로딩 있음)`() {
        Truth.assertThat(
            productList.addData(
                mutableListOf(
                    Product(
                        1,
                        "10000",
                        "",
                        "test1",
                        100,
                        0,
                        0
                    ),
                    Product(
                        2,
                        "10000",
                        "",
                        "test2",
                        100,
                        0,
                        0
                    )
                )
            )
        ).hasSize(3)
    }

    @Test
    fun `clearData (로딩 있음)`() {
        Truth.assertThat(productList.clearData()).hasSize(1)
    }

    @Test
    fun `removeLoading (로딩 없음)`() {
        Truth.assertThat(productList.removeLoading()).hasSize(0)
    }

    @Test
    fun `addData 이후 removeLoading (로딩 없음)`() {
        Truth.assertThat(
            productList.addData(
                mutableListOf(
                    Product(
                        1,
                        "10000",
                        "",
                        "test1",
                        100,
                        0,
                        0
                    ),
                    Product(
                        2,
                        "10000",
                        "",
                        "test2",
                        100,
                        0,
                        0
                    )
                )
            )
        ).hasSize(3)
        Truth.assertThat(productList.removeLoading()).hasSize(2)
    }

    @Test
    fun `addData 이후 clearData (로딩 있음)`() {
        Truth.assertThat(
            productList.addData(
                mutableListOf(
                    Product(
                        1,
                        "10000",
                        "",
                        "test1",
                        100,
                        0,
                        0
                    ),
                    Product(
                        2,
                        "10000",
                        "",
                        "test2",
                        100,
                        0,
                        0
                    )
                )
            )
        ).hasSize(3)
        Truth.assertThat(productList.clearData()).hasSize(1)
    }

    @Test
    fun `addData 이후 removeLoading 이후 clearData (로딩 있음)`() {
        Truth.assertThat(
            productList.addData(
                mutableListOf(
                    Product(
                        1,
                        "10000",
                        "",
                        "test1",
                        100,
                        0,
                        0
                    ),
                    Product(
                        2,
                        "10000",
                        "",
                        "test2",
                        100,
                        0,
                        0
                    )
                )
            )
        ).hasSize(3)
        Truth.assertThat(productList.removeLoading()).hasSize(2)
        Truth.assertThat(productList.clearData()).hasSize(1)
    }

    @Test
    fun `addData 이후 clearData 이후 removeLoading (로딩 없음)`() {
        Truth.assertThat(
            productList.addData(
                mutableListOf(
                    Product(
                        1,
                        "10000",
                        "",
                        "test1",
                        100,
                        0,
                        0
                    ),
                    Product(
                        2,
                        "10000",
                        "",
                        "test2",
                        100,
                        0,
                        0
                    )
                )
            )
        ).hasSize(3)
        Truth.assertThat(productList.clearData()).hasSize(1)
        Truth.assertThat(productList.removeLoading()).hasSize(0)
    }

    @Test
    fun `removeLoading 이후 addData (로딩 있음)`() {
        Truth.assertThat(productList.clearData()).hasSize(1)
        Truth.assertThat(productList.removeLoading()).hasSize(0)
        Truth.assertThat(
            productList.addData(
                mutableListOf(
                    Product(
                        1,
                        "10000",
                        "",
                        "test1",
                        100,
                        0,
                        0
                    ),
                    Product(
                        2,
                        "10000",
                        "",
                        "test2",
                        100,
                        0,
                        0
                    )
                )
            )
        ).hasSize(3)
    }
}
