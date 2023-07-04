package com.search.images.search_image.di

import com.search.images.search_image.data.PixbayImageRepository
import com.search.images.search_image.data.PixbayImageRepositoryImpl
import com.search.images.search_image.data.PixbayImageSearchAPI
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
interface SearchImageModule {

    @Binds
    fun bindPixbayImageRepository(
        pixbayImageRepositoryImpl: PixbayImageRepositoryImpl
    ): PixbayImageRepository

    companion object {

        @Provides
        @Singleton
        fun providePixbayImageSearchAPI(retrofit: Retrofit): PixbayImageSearchAPI {
            return retrofit.create(PixbayImageSearchAPI::class.java)
        }
    }
}