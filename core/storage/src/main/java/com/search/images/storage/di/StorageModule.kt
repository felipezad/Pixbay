package com.search.images.storage.di

import com.search.images.storage.CacheStorage
import com.search.images.storage.Storage
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface StorageModule {

    @Binds
    @Singleton
    fun bindStorage(storage: CacheStorage): Storage
}