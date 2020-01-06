package com.runeanim.birdviewproject.di

import android.content.Context
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import javax.inject.Singleton


@Module
class ApplicationModule {

    @Singleton
    @Provides
    fun provideCache(context: Context): Cache {
        return Cache(context.cacheDir, 10L * 1024 * 1024)
    }
}