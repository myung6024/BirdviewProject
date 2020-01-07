package com.runeanim.birdviewproject.di

import android.content.Context
import com.runeanim.birdviewproject.BirdApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        NetworkModule::class,
        ServiceModule::class,
        ProductsModule::class
    ])
interface ApplicationComponent : AndroidInjector<BirdApplication> {

    override fun inject(application: BirdApplication)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }
}