package com.runeanim.birdviewproject.productdetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.runeanim.birdviewproject.FakeRepository
import com.runeanim.birdviewproject.LiveDataTestUtil
import com.runeanim.birdviewproject.R
import com.runeanim.birdviewproject.SchedulersRule
import com.runeanim.birdviewproject.data.ProductDetail
import com.runeanim.birdviewproject.ui.productdetail.ProductDetailViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ProductDetailViewModelTest {

    private lateinit var productDetailViewModel: ProductDetailViewModel

    private lateinit var productsRepository: FakeRepository

    @Rule
    @JvmField
    val testSchedulerRule = SchedulersRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {
        productsRepository = FakeRepository()
        productDetailViewModel = ProductDetailViewModel(productsRepository)
    }

    @Test
    fun `초기화`() {
        productDetailViewModel.start(100)
        Truth.assertThat((LiveDataTestUtil.getValue(productDetailViewModel.product) as ProductDetail).id).isEqualTo(100)
    }

    @Test
    fun `초기화 실패`() {
        productDetailViewModel.start(null)
        Truth.assertThat(LiveDataTestUtil.getValue(productDetailViewModel.product)).isNull()
    }

    @Test
    fun `400 에러 테스트`() {
        productsRepository.setErrorState(400)
        productDetailViewModel.start(100)
        Truth.assertThat(LiveDataTestUtil.getValue(productDetailViewModel.snackbarText).getContentIfNotHandled())
            .isEqualTo(R.string.failed_load_data_400)
    }

    @Test
    fun `404 에러 테스트 (결과가 없음)`() {
        productsRepository.setErrorState(404)
        productDetailViewModel.start(100)
        Truth.assertThat(LiveDataTestUtil.getValue(productDetailViewModel.snackbarText).getContentIfNotHandled())
            .isEqualTo(R.string.failed_load_data_404)
    }

    @Test
    fun `500 에러 테스트`() {
        productsRepository.setErrorState(500)
        productDetailViewModel.start(100)
        Truth.assertThat(LiveDataTestUtil.getValue(productDetailViewModel.snackbarText).getContentIfNotHandled())
            .isEqualTo(R.string.failed_load_data_500)
    }
}
