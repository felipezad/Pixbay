package com.search.images.search_image

import com.search.images.search_image.data.PixbayImage
import com.search.images.search_image.data.PixbayImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class ImageRepositoryTest {

    private lateinit var imageRepository: PixbayImageRepository

    @Before
    fun setUp() {
        imageRepository = FakeRepository()
    }

    @Test
    fun `When search is triggered then images are returned as flow`() {
        runTest {
            // Given
            val query = "kittens"

            // then
            val images = imageRepository.searchImages(query)

            //assert that the images are not null and are of type Flow<List<PixbayImage>>
            assert(images != null)
            assert(images is Flow<List<PixbayImage>>)
        }
    }
}

private class FakeRepository : PixbayImageRepository {
    override suspend fun searchImages(query: String): Flow<List<PixbayImage>>? {
        return flow {
            val fakeImages = mutableListOf<PixbayImage>()
            for (i in 1..10) {
                fakeImages.add(
                    PixbayImage(
                        id = i,
                        user = "User $i",
                        tags = "Tags $i",
                        thumbnail = "https://fake.photos?random=$i",
                        likes = i * 100,
                        downloads = i * 50,
                        comments = i * 10,
                        imageURL = "https://fake.photos?random=$i"
                    )
                )
            }
            emit(fakeImages)
        }
    }
}