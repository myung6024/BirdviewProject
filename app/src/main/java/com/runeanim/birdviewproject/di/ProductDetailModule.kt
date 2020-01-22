package com.runeanim.birdviewproject.di

import androidx.lifecycle.ViewModel
import com.runeanim.birdviewproject.ui.productdetail.ProductDetailFragment
import com.runeanim.birdviewproject.ui.productdetail.ProductDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class ProductDetailModule {

    @ContributesAndroidInjector(modules = [
        ViewModelBuilder::class
    ])
    internal abstract fun productDetailFragment(): ProductDetailFragment

    @Binds
    @IntoMap
    @ViewModelKey(ProductDetailViewModel::class)
    abstract fun bindViewModel(viewmodel: ProductDetailViewModel): ViewModel
}