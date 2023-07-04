package com.search.images.search_image.data

import retrofit2.http.GET
import retrofit2.http.Query

interface PixbayImageSearchAPI {
    @GET("api")
    suspend fun searchImages(@Query("q") query: String): PixabayResponse
}
