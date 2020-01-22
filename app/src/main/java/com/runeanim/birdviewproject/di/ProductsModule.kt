package com.runeanim.birdviewproject.di

import androidx.lifecycle.ViewModel
import com.runeanim.birdviewproject.ui.products.ProductsFragment
import com.runeanim.birdviewproject.ui.products.ProductsViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class ProductsModule {

    @ContributesAndroidInjector(modules = [
        ViewModelBuilder::class
        ])
    internal abstract fun productsFragment(): ProductsFragment

    @Binds
    @IntoMap
    @ViewModelKey(ProductsViewModel::class)
    abstract fun bindViewModel(viewmodel: ProductsViewModel): ViewModel
}
