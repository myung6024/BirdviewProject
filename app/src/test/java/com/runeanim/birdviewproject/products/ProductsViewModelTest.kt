package com.runeanim.birdviewproject.products

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.runeanim.birdviewproject.FakeRepository
import com.runeanim.birdviewproject.SchedulersRule
import com.runeanim.birdviewproject.ui.products.ProductsViewModel
import com.google.common.truth.Truth.assertThat
import com.runeanim.birdviewproject.LiveDataTestUtil
import com.runeanim.birdviewproject.R
import com.runeanim.birdviewproject.data.Product
import com.runeanim.birdviewproject.ui.products.SkinFilterType
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ProductsViewModelTest {

    private lateinit var productsViewModel: ProductsViewModel

    private lateinit var productsRepository: FakeRepository

    @Rule
    @JvmField
    val testSchedulerRule = SchedulersRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {
        productsRepository = FakeRepository()

        productsRepository.setProducts(
            listOf(
                Product(1, "10000", "", "test1", 100, 0, 0),
                Product(2, "10000", "", "test2", 100, 0, 0),
                Product(3, "10000", "", "test3", 100, 0, 0),
                Product(4, "10000", "", "test4", 100, 0, 0),
                Product(5, "10000", "", "test5", 100, 0, 0),
                Product(6, "10000", "", "test6", 100, 0, 0),
                Product(7, "10000", "", "test7", 100, 0, 0),
                Product(1, "10000", "", "test1", 100, 0, 0),
                Product(2, "10000", "", "test2", 100, 0, 0),
                Product(3, "10000", "", "test3", 100, 100, 0),
                Product(4, "10000", "", "test4", 0, 100, 0),
                Product(5, "10000", "", "test5", 0, 100, 0),
                Product(6, "10000", "", "test6", 0, 100, 0),
                Product(7, "10000", "", "test7", 0, 100, 0),
                Product(1, "10000", "", "test1", 0, 100, 0),
                Product(2, "10000", "", "test2", 0, 100, 0),
                Product(3, "10000", "", "test3", 0, 0, 100),
                Product(4, "10000", "", "test4", 0, 0, 100),
                Product(5, "10000", "", "test5", 0, 0, 100),
                Product(6, "10000", "", "test6", 0, 0, 100),
                Product(7, "10000", "", "test7", 0, 0, 100)
            )
        )
        productsViewModel = ProductsViewModel(productsRepository)
    }

    @Test
    fun `시작해서 Skin Type이 OILY로 기본 설정되어 있는지 확인하고 init()으로 데이터 불러오기`() {
        assertThat(LiveDataTestUtil.getValue(productsViewModel.skinType)).isEqualTo(SkinFilterType.OILY)
        assertThat(LiveDataTestUtil.getValue(productsViewModel.items)).hasSize(10)
    }

    @Test
    fun `SkinType OILY로 변경하기`() {
        productsViewModel.changeType(SkinFilterType.OILY)
        assertThat(LiveDataTestUtil.getValue(productsViewModel.skinType)).isEqualTo(SkinFilterType.OILY)
        assertThat(LiveDataTestUtil.getValue(productsViewModel.items)).hasSize(10)
    }

    @Test
    fun `SkinType DRY로 변경하기`() {
        productsViewModel.changeType(SkinFilterType.DRY)
        assertThat(LiveDataTestUtil.getValue(productsViewModel.skinType)).isEqualTo(SkinFilterType.DRY)
        assertThat(LiveDataTestUtil.getValue(productsViewModel.items)).hasSize(7)
    }

    @Test
    fun `SkinType SENSITIVE로 변경하기`() {
        productsViewModel.changeType(SkinFilterType.SENSITIVE)
        assertThat(LiveDataTestUtil.getValue(productsViewModel.skinType)).isEqualTo(SkinFilterType.SENSITIVE)
        assertThat(LiveDataTestUtil.getValue(productsViewModel.items)).hasSize(5)
    }

    @Test
    fun `OILY에서 "test"로 검색하기`() {
        productsViewModel.searchData("test")
        assertThat(LiveDataTestUtil.getValue(productsViewModel.skinType)).isEqualTo(SkinFilterType.OILY)
        assertThat(LiveDataTestUtil.getValue(productsViewModel.items)).hasSize(10)
    }

    @Test
    fun `OILY에서 "test1"로 검색하기`() {
        productsViewModel.searchData("test1")
        assertThat(LiveDataTestUtil.getValue(productsViewModel.skinType)).isEqualTo(SkinFilterType.OILY)
        assertThat(LiveDataTestUtil.getValue(productsViewModel.items)).hasSize(2)
    }

    @Test
    fun `DRY에서 "test"로 검색하기`() {
        productsViewModel.changeType(SkinFilterType.DRY)
        productsViewModel.searchData("test")
        assertThat(LiveDataTestUtil.getValue(productsViewModel.skinType)).isEqualTo(SkinFilterType.DRY)
        assertThat(LiveDataTestUtil.getValue(productsViewModel.items)).hasSize(7)
    }

    @Test
    fun `DRY에서 "test1"로 검색하기`() {
        productsViewModel.changeType(SkinFilterType.DRY)
        productsViewModel.searchData("test1")
        assertThat(LiveDataTestUtil.getValue(productsViewModel.skinType)).isEqualTo(SkinFilterType.DRY)
        assertThat(LiveDataTestUtil.getValue(productsViewModel.items)).hasSize(1)
    }

    @Test
    fun `SENSITIVE에서 "test"로 검색하기`() {
        productsViewModel.changeType(SkinFilterType.SENSITIVE)
        productsViewModel.searchData("test")
        assertThat(LiveDataTestUtil.getValue(productsViewModel.skinType)).isEqualTo(SkinFilterType.SENSITIVE)
        assertThat(LiveDataTestUtil.getValue(productsViewModel.items)).hasSize(5)
    }

    @Test
    fun `SENSITIVE에서 "test1"로 검색하기`() {
        productsViewModel.changeType(SkinFilterType.SENSITIVE)
        productsViewModel.searchData("test1")
        assertThat(LiveDataTestUtil.getValue(productsViewModel.skinType)).isEqualTo(SkinFilterType.SENSITIVE)
        assertThat(LiveDataTestUtil.getValue(productsViewModel.items)).hasSize(0)
    }

    @Test
    fun `OILY에서 "test"로 검색하고 DRY로 변경하기`() {
        productsViewModel.searchData("test")
        assertThat(LiveDataTestUtil.getValue(productsViewModel.skinType)).isEqualTo(SkinFilterType.OILY)
        assertThat(LiveDataTestUtil.getValue(productsViewModel.items)).hasSize(10)

        productsViewModel.changeType(SkinFilterType.DRY)
        assertThat(LiveDataTestUtil.getValue(productsViewModel.skinType)).isEqualTo(SkinFilterType.DRY)
        assertThat(LiveDataTestUtil.getValue(productsViewModel.items)).hasSize(7)
    }

    @Test
    fun `OILY에서 "test1"로 검색하고 DRY로 변경하기`() {
        productsViewModel.searchData("test1")
        assertThat(LiveDataTestUtil.getValue(productsViewModel.skinType)).isEqualTo(SkinFilterType.OILY)
        assertThat(LiveDataTestUtil.getValue(productsViewModel.items)).hasSize(2)

        productsViewModel.changeType(SkinFilterType.DRY)
        assertThat(LiveDataTestUtil.getValue(productsViewModel.skinType)).isEqualTo(SkinFilterType.DRY)
        assertThat(LiveDataTestUtil.getValue(productsViewModel.items)).hasSize(1)
    }

    @Test
    fun `400 에러 테스트`() {
        productsRepository.setErrorState(400)
        productsViewModel.changeType(SkinFilterType.DRY)
        assertThat(LiveDataTestUtil.getValue(productsViewModel.snackbarText).getContentIfNotHandled()).isEqualTo(R.string.failed_load_data_400)
        assertThat(LiveDataTestUtil.getValue(productsViewModel.skinType)).isEqualTo(SkinFilterType.DRY)
        assertThat(LiveDataTestUtil.getValue(productsViewModel.items)).hasSize(0)
    }

    @Test
    fun `404 에러 테스트 (결과가 없음)`() {
        productsRepository.setErrorState(404)
        productsViewModel.changeType(SkinFilterType.DRY)
        assertThat(LiveDataTestUtil.getValue(productsViewModel.snackbarText).getContentIfNotHandled()).isEqualTo(R.string.failed_load_data_404_on_search)
        assertThat(LiveDataTestUtil.getValue(productsViewModel.skinType)).isEqualTo(SkinFilterType.DRY)
        assertThat(LiveDataTestUtil.getValue(productsViewModel.items)).hasSize(0)
    }

    @Test
    fun `더 불러오기 404 에러 테스트 (더 불러올 데이터가 없음)`() {
        assertThat(LiveDataTestUtil.getValue(productsViewModel.skinType)).isEqualTo(SkinFilterType.OILY)
        assertThat(LiveDataTestUtil.getValue(productsViewModel.items)).hasSize(10)
        productsRepository.setErrorState(404)
        productsViewModel.loadData()
        assertThat(LiveDataTestUtil.getValue(productsViewModel.skinType)).isEqualTo(SkinFilterType.OILY)
        assertThat(LiveDataTestUtil.getValue(productsViewModel.items)).hasSize(10)
        assertThat(LiveDataTestUtil.getValue(productsViewModel.snackbarText).getContentIfNotHandled()).isEqualTo(R.string.failed_load_data_404)
    }

    @Test
    fun `500 에러 테스트`() {
        productsRepository.setErrorState(500)
        productsViewModel.changeType(SkinFilterType.DRY)
        assertThat(LiveDataTestUtil.getValue(productsViewModel.snackbarText).getContentIfNotHandled()).isEqualTo(R.string.failed_load_data_500)
        assertThat(LiveDataTestUtil.getValue(productsViewModel.skinType)).isEqualTo(SkinFilterType.DRY)
        assertThat(LiveDataTestUtil.getValue(productsViewModel.items)).hasSize(0)
    }
}