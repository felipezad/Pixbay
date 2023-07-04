package com.search.images.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pixbay_image")
data class PixbayImageEntity(
    @PrimaryKey val id: Int?,
    @ColumnInfo(name = "user") val user: String?,
    @ColumnInfo(name = "tags") val tags: String?,
    @ColumnInfo(name = "thumbnail") val thumbnail: String?,
    @ColumnInfo(name = "likes") val likes: Int?,
    @ColumnInfo(name = "downloads") val downloads: Int?,
    @ColumnInfo(name = "comments") val comments: Int?,
    @ColumnInfo(name = "imageURL") val imageURL: String?
)
