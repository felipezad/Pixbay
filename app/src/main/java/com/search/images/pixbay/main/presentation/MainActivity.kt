package com.search.images.pixbay.main.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.fragment.NavHostFragment
import com.search.images.navigation.ImageDetailsNavigation
import com.search.images.navigation.ImageDetailsNavigation.Companion.IMAGE_DETAILS_ARGS
import com.search.images.pixbay.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ImageDetailsNavigation {

    private val navController by lazy {
        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
    }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onImageDetailsClicked() {
        val imageDetails = bundleOf(IMAGE_DETAILS_ARGS to viewModel.retrieveImageDetails())
        navController.navigate(R.id.action_searchImageFragment_to_imageDetailFragment, imageDetails)
    }
}
