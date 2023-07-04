package com.search.images.search_image.di

import com.search.images.search_image.SearchImageViewFeedback
import com.search.images.search_image.SearchImageViewFeedbackImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
interface SearchImageFeedbackModule {

    companion object {

        @Provides
        @ActivityRetainedScoped
        fun provideSearchImageFeedback(): SearchImageViewFeedback {
            return SearchImageViewFeedbackImpl()
        }
    }
}