package com.search.images.search_image.data

import android.util.Log
import com.search.images.core.network.IODispatcher
import com.search.images.database.dao.PixbayImageDAO
import com.search.images.database.model.PixbayImageEntity
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class PixbayImageRepositoryImpl @Inject constructor(
    private val pixbayImageSearchAPI: PixbayImageSearchAPI,
    private val pixbayImageDAO: PixbayImageDAO,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
) : PixbayImageRepository {

    override suspend fun searchImages(query: String): Flow<List<PixbayImage>>? {
        return withContext(ioDispatcher) {
            searchImagesFromAPI(query)
            loadImagesFromDatabase()
        }
    }

    private suspend fun loadImagesFromDatabase() = try {
        pixbayImageDAO.getAll().map { it.map { it.asDomainModel() } }
    } catch (e: Exception) {
        Log.e("PixbayImageRepository", "Error getting images from database", e)
        null
    }

    private suspend fun searchImagesFromAPI(query: String) {
        try {
            val list = pixbayImageSearchAPI.searchImages(query).hits.map {
                it.toDomainModel()
            }
            val listInsert =
                pixbayImageDAO.insertAll(*list.map { it.asEntity() }.toTypedArray())
            Log.d("PixbayImageRepository", "listInsert: $listInsert")
        } catch (e: Exception) {
            Log.e("PixbayImageRepository", "Error searching images", e)
        }
    }

    private fun Hit.toDomainModel(): PixbayImage {
        return PixbayImage(
            id = id,
            user = user,
            tags = tags,
            thumbnail = previewURL,
            likes = likes,
            downloads = downloads,
            comments = comments,
            imageURL = imageURL
        )
    }

    private fun PixbayImageEntity.asDomainModel(): PixbayImage {
        return PixbayImage(
            id = id,
            user = user,
            tags = tags,
            thumbnail = thumbnail,
            likes = likes,
            downloads = downloads,
            comments = comments,
            imageURL = imageURL,
        )
    }
}
