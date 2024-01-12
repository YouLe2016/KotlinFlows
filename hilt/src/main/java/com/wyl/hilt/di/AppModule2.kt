package com.wyl.hilt.di

import com.wyl.hilt.service.AServiceImpl
import com.wyl.hilt.service.IDemoService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppModule2 {
    @Binds
    // 没有这个注解的话，创建的是不同的对象
    @Singleton
    fun bindDemoService(service: AServiceImpl): IDemoService
}

/*
@Module
@InstallIn(ActivityComponent::class)
interface AppModule2 {
    @Binds
    // 没有这个注解的话，创建的是不同的对象
    @ActivityScoped
    fun bindDemoService(service: AServiceImpl): IDemoService
}*/
