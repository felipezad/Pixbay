package com.search.images.core.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.Call
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule {

    companion object {

        @Provides
        @Singleton
        fun provideRetrofit(okhttpCallFactory: Call.Factory): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://pixabay.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .callFactory(okhttpCallFactory)
                .build()
        }

        @Provides
        @Singleton
        fun okHttpCallFactory(): Call.Factory = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val originalUrl = originalRequest.url()

                // This is not safe but for sake of simplicity we are doing it.
                // ideally we should set this in the environment of the CI and not in the code.
                val newUrl = originalUrl.newBuilder()
                    .addQueryParameter("key", "38068586-7f097c7e3c55d8120f198e0f2")
                    .build()

                val newRequest = originalRequest.newBuilder()
                    .url(newUrl)
                    .build()

                chain.proceed(newRequest)
            }
            .build()
    }
}
