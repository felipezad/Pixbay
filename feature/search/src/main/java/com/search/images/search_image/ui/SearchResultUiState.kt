package com.search.images.search_image.ui

import com.search.images.search_image.data.PixbayImage

sealed interface SearchResultUiState {
    object InitialState : SearchResultUiState
    object Loading : SearchResultUiState
    object EmptyQuery : SearchResultUiState
    object EmptyResult : SearchResultUiState
    object LoadFailed : SearchResultUiState
    object NavigateToImageDetails : SearchResultUiState

    data class Success(
        val data: Set<PixbayImage> = emptySet()
    ) : SearchResultUiState
}
