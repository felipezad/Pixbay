package com.search.images.search_image.domain

import com.search.images.search_image.data.PixbayImageRepository
import java.net.URLEncoder
import javax.inject.Inject

class SearchImageByTermUseCase @Inject constructor(
    private val pixbayImageRepository: PixbayImageRepository
) {

    suspend operator fun invoke(query: String) = pixbayImageRepository.searchImages(
        URLEncoder.encode(query, "UTF-8")
    )
}