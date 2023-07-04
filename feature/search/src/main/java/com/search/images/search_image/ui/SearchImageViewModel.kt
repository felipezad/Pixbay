package com.search.images.search_image.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.search.images.navigation.ImageDetailsNavigation.Companion.IMAGE_DETAILS_ARGS
import com.search.images.search_image.data.PixbayImage
import com.search.images.search_image.data.toImageDetailsArgs
import com.search.images.search_image.domain.SearchImageByTermUseCase
import com.search.images.storage.Storage
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class SearchImageViewModel @Inject constructor(
    private val searchImageUseCase: SearchImageByTermUseCase,
    private val cacheStorage: Storage
) : ViewModel() {

    var query: String = ""
        private set

    private var _searchStateFlow: MutableStateFlow<SearchResultUiState> =
        MutableStateFlow(SearchResultUiState.InitialState)
    val searchStateFlow: StateFlow<SearchResultUiState> = _searchStateFlow

    private val currentList = mutableListOf<PixbayImage>()

    fun searchImages(query: String) {
        Log.d("SearchImageViewModel", "searchImages: $query")
        this.query = query
        _searchStateFlow.value = SearchResultUiState.Loading
        viewModelScope.launch {
            val searchImageUseCase = searchImageUseCase(query)
            searchImageUseCase ?: run {
                _searchStateFlow.value = SearchResultUiState.LoadFailed
                return@launch
            }
            searchImageUseCase.collect { result: List<PixbayImage> ->
                Log.d("SearchImageViewModel", "result: $result")
                when {
                    result.isEmpty() -> {
                        _searchStateFlow.value = SearchResultUiState.EmptyResult
                    }

                    else -> {
                        if (currentList.isEmpty()) {
                            currentList.addAll(0, result)
                            _searchStateFlow.value = SearchResultUiState.Success(result.toSet())
                            return@collect
                        } else {
                            currentList.containsAll(result).let { containsAll ->
                                if (!containsAll) {
                                    currentList.addAll(0, result)
                                    _searchStateFlow.value =
                                        SearchResultUiState.Success(currentList.toSet())
                                } else {
                                    _searchStateFlow.value =
                                        SearchResultUiState.EmptyResult
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    fun saveImageDetails(image: PixbayImage) {
        cacheStorage.put(IMAGE_DETAILS_ARGS, image.toImageDetailsArgs())
        _searchStateFlow.value = SearchResultUiState.NavigateToImageDetails
    }

    fun navigationCompleted() {
        _searchStateFlow.value = SearchResultUiState.Success(currentList.toSet())
        query = ""
    }
}
