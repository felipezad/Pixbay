package com.search.images.search_image.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.search.images.search_image.SearchImageViewFeedback
import com.search.images.search_image.data.PixbayImage
import com.search.images.search_image.databinding.ItemSearchImageBinding

class PixabayImageAdapter(
    images: List<PixbayImage>,
    private val glide: RequestManager,
    private val searchImageViewFeedback: SearchImageViewFeedback,
    private val saveImageDetails: (PixbayImage) -> Unit
) :
    RecyclerView.Adapter<PixabayImageAdapter.ViewHolder>() {

    private var mutableImages: MutableList<PixbayImage> = images.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemSearchImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val image = mutableImages[position]
        holder.bind(image)
    }

    override fun getItemCount(): Int {
        return mutableImages.size
    }

    fun updateData(images: Set<PixbayImage>) {
        this.mutableImages = images.toMutableList()
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val itemSearchBinding: ItemSearchImageBinding) :
        RecyclerView.ViewHolder(itemSearchBinding.root) {
        fun bind(image: PixbayImage) {
            glide.load(image.thumbnail).into(itemSearchBinding.ivThumbnail)
            itemSearchBinding.tvTags.text = image.tags
            itemSearchBinding.tvUser.text = image.user
            itemSearchBinding.root.setOnClickListener {
                searchImageViewFeedback.saveImageDetailsSnackbar(
                    itemSearchBinding.root,
                    "Wanna check the details?",
                    "Details Image",
                    saveImageDetails,
                    image
                )
            }
        }
    }
}
