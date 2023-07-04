package com.search.images.search_image.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.search.images.navigation.ImageDetailsNavigation
import com.search.images.search_image.SearchImageViewFeedback
import com.search.images.search_image.databinding.FragmentSearchImageBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchImageFragment : Fragment() {

    private var _binding: FragmentSearchImageBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchImageViewModel by viewModels()

    private lateinit var listenerImageDetailsNavigation: ImageDetailsNavigation

    @Inject
    lateinit var searchImageViewFeedback: SearchImageViewFeedback

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchImageBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
        initView()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listenerImageDetailsNavigation = context as ImageDetailsNavigation
    }

    private fun initView() {
        binding.etSearchImage.setText(viewModel.query)
        binding.rvSearchImage.adapter =
            PixabayImageAdapter(
                emptyList(),
                Glide.with(this),
                searchImageViewFeedback,
                viewModel::saveImageDetails
            )
        binding.rvSearchImage.layoutManager = LinearLayoutManager(this.context)

        binding.etSearchImage.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_NEXT || actionId == EditorInfo.IME_ACTION_DONE) {
                    viewModel.searchImages(v?.text.toString())
                    return true
                }
                return false
            }
        })
    }

    private fun initObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.searchStateFlow.collect { uiState ->
                    when (uiState) {

                        SearchResultUiState.InitialState -> {
                            viewModel.searchImages("Fruits")
                        }

                        SearchResultUiState.EmptyQuery -> {
                            Log.d("SearchImageFragment", "EmptyQuery")
                            binding.progressBar.visibility = View.GONE
                            searchImageViewFeedback.displayToast(
                                requireContext(),
                                "To display new images, send the query"
                            )
                        }

                        SearchResultUiState.EmptyResult -> {
                            Log.d("SearchImageFragment", "EmptyResult")
                            binding.progressBar.visibility = View.GONE
                            searchImageViewFeedback.displayToast(
                                requireContext(),
                                "There is no new resources, loading the current database"
                            )
                        }

                        SearchResultUiState.LoadFailed -> {
                            Log.d("SearchImageFragment", "LoadFailed")
                            binding.progressBar.visibility = View.GONE
                            searchImageViewFeedback.displayToast(
                                requireContext(),
                                "Failed to load online resources"
                            )
                        }

                        SearchResultUiState.Loading -> {
                            Log.d("SearchImageFragment", "Loading")
                            binding.progressBar.visibility = View.VISIBLE
                        }

                        is SearchResultUiState.Success -> {
                            Log.d("SearchImageFragment", "Success")
                            (binding.rvSearchImage.adapter as PixabayImageAdapter).updateData(
                                uiState.data
                            )
                            binding.progressBar.visibility = View.GONE
                        }

                        SearchResultUiState.NavigateToImageDetails -> {
                            listenerImageDetailsNavigation.onImageDetailsClicked()
                            viewModel.navigationCompleted()
                        }
                    }
                }
            }
        }
    }
}
