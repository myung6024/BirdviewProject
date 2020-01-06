package com.runeanim.birdviewproject.di

import android.content.Context
import com.runeanim.birdviewproject.data.source.DefaultProductsRepository
import com.runeanim.birdviewproject.data.source.ProductsDataSource
import com.runeanim.birdviewproject.data.source.ProductsRepository
import com.runeanim.birdviewproject.data.source.remote.BirdViewService
import com.runeanim.birdviewproject.data.source.remote.ProductsRemoteDataSource
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.Cache
import java.text.DecimalFormat
import javax.inject.Qualifier
import javax.inject.Singleton


@Module(includes = [ApplicationModuleBinds::class])
class ApplicationModule {

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class ProductsRemoteDataSource

    @Singleton
    @Provides
    fun provideCache(context: Context): Cache {
        return Cache(context.cacheDir, 10L * 1024 * 1024)
    }

    @Singleton
    @Provides
    fun providePriceFormat(): DecimalFormat {
        return DecimalFormat("###,###")
    }

    @Singleton
    @ProductsRemoteDataSource
    @Provides
    fun provideProductsRemoteDataSource(
        birdViewService: BirdViewService
    ): ProductsDataSource {
        return ProductsRemoteDataSource(birdViewService)
    }
}

@Module
abstract class ApplicationModuleBinds {

    @Singleton
    @Binds
    abstract fun bindRepository(repo: DefaultProductsRepository): ProductsRepository
}
