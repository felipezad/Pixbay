package com.search.images.model.navigation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class ImageDetailsArgs(
    val id: Int?,
    val user: String?,
    val tags: String?,
    val thumbnail: String?,
    val likes: Int?,
    val downloads: Int?,
    val comments: Int?,
    val imageURL: String?
) : Parcelable