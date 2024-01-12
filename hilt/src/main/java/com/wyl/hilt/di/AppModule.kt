package com.wyl.hilt.di

import com.wyl.hilt.di.anno.Apple
import com.wyl.hilt.di.anno.Pear
import com.wyl.hilt.service.Fruit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
class AppModule {
    @Provides
    @Pear
    fun providePear(): String {
        return "梨"
    }

    @Provides
    @Apple
    fun provideApple(): String {
        return "苹果"
    }

    @Provides
    fun provideFruit(@Apple name: String): Fruit {
        return Fruit(name)
    }
}