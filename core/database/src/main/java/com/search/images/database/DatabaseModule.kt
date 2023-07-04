package com.search.images.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DatabaseModule {
    companion object {
        @Provides
        @Singleton
        fun providesPixbayImageDatabaseDatabase(
            @ApplicationContext context: Context,
        ): PixbayImageDatabase = Room.databaseBuilder(
            context,
            PixbayImageDatabase::class.java,
            "pixbayimage-database",
        ).build()
    }
}
