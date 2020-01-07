package com.runeanim.birdviewproject.di

import com.runeanim.birdviewproject.remote.BirdViewService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ServiceModule {

    @Singleton
    @Provides
    fun provideBirdViewService(retrofit: Retrofit): BirdViewService {
        return retrofit.create(BirdViewService::class.java)
    }
}