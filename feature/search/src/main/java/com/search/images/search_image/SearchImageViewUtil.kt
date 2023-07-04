package com.search.images.search_image

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.search.images.search_image.data.PixbayImage
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchImageViewFeedbackImpl @Inject constructor() : SearchImageViewFeedback {

    override fun displayToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun saveImageDetailsSnackbar(
        view: View,
        message: String,
        actionName: String,
        action: (PixbayImage) -> Unit,
        image: PixbayImage
    ) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
            .setAction(actionName, { action(image) })
            .show()
    }
}

interface SearchImageViewFeedback {
    fun displayToast(context: Context, message: String)
    fun saveImageDetailsSnackbar(
        view: View,
        message: String,
        actionName: String,
        action: (PixbayImage) -> Unit,
        image: PixbayImage
    )
}
