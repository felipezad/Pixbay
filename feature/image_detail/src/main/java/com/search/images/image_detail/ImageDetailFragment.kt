package com.search.images.image_detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.search.images.image_detail.databinding.FragmentImageDetailBinding
import com.search.images.model.navigation.ImageDetailsArgs
import com.search.images.navigation.ImageDetailsNavigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageDetailFragment : Fragment() {

    private var _binding: FragmentImageDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImageDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        val imageDetails =
            arguments?.getParcelable<ImageDetailsArgs>(ImageDetailsNavigation.IMAGE_DETAILS_ARGS)
        imageDetails ?: return
        Glide.with(this).load(imageDetails.thumbnail).into(binding.ivDetailImage)
        with(binding) {
            tvUserNameDetail.text = imageDetails.user
            tvTagsDetails.text = imageDetails.tags
            tvLikesDetails.text = imageDetails.likes.toString()
            tvCommentsDetails.text = imageDetails.comments.toString()
            tvDownloadsDetails.text = imageDetails.downloads.toString()
        }
        Log.d("ImageDetailFragment", "imageDetails: $imageDetails")
    }
}