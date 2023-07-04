package com.search.images.search_image.data

import kotlinx.coroutines.flow.Flow

interface PixbayImageRepository {

    suspend fun searchImages(query: String): Flow<List<PixbayImage>>?
}