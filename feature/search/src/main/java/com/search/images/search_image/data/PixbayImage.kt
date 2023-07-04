package com.search.images.search_image.data

import com.search.images.database.model.PixbayImageEntity
import com.search.images.model.navigation.ImageDetailsArgs

data class PixbayImage(
    val id: Int?,
    val user: String?,
    val tags: String?,
    val thumbnail: String?,
    val likes: Int?,
    val downloads: Int?,
    val comments: Int?,
    val imageURL: String?
)

fun PixbayImage.asEntity() = PixbayImageEntity(
    id = id,
    user = user,
    tags = tags,
    thumbnail = thumbnail,
    likes = likes,
    downloads = downloads,
    comments = comments,
    imageURL = imageURL
)

fun PixbayImage.toImageDetailsArgs() = ImageDetailsArgs(
    id = id,
    user = user,
    tags = tags,
    thumbnail = thumbnail,
    likes = likes,
    downloads = downloads,
    comments = comments,
    imageURL = imageURL
)
