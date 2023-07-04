package com.search.images.pixbay.main.presentation

import androidx.lifecycle.ViewModel
import com.search.images.model.navigation.ImageDetailsArgs
import com.search.images.navigation.ImageDetailsNavigation
import com.search.images.storage.Storage
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val cacheStorage: Storage) : ViewModel() {

    fun retrieveImageDetails(): ImageDetailsArgs? {
        return cacheStorage.get<ImageDetailsArgs>(ImageDetailsNavigation.IMAGE_DETAILS_ARGS)
    }
}