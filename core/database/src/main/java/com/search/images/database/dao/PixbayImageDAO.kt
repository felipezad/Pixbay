package com.search.images.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.search.images.database.model.PixbayImageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PixbayImageDAO {

    @Query("SELECT * FROM pixbay_image")
    fun getAll(): Flow<List<PixbayImageEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg images: PixbayImageEntity): List<Long>
}