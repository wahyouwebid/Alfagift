package com.ujangwahyu.app.features.source.presentation.ui

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ujangwahyu.app.R
import com.ujangwahyu.app.common.base.BaseFragment
import com.ujangwahyu.app.common.base.BaseResultState
import com.ujangwahyu.app.common.utils.ErrorMapper.errorMapper
import com.ujangwahyu.app.common.utils.hide
import com.ujangwahyu.app.common.utils.hideKeyboard
import com.ujangwahyu.app.common.utils.parcelable
import com.ujangwahyu.app.common.utils.show
import com.ujangwahyu.app.databinding.FragmentSourceBinding
import com.ujangwahyu.app.features.categories.domain.model.CategoriesItem
import com.ujangwahyu.app.features.source.domain.model.SourceItem
import com.ujangwahyu.app.features.source.presentation.adapter.SourceAdapter
import com.ujangwahyu.app.features.source.presentation.viewmodel.SourceViewModel
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.HttpException
import java.io.IOException

@AndroidEntryPoint
class SourceFragment: BaseFragment<FragmentSourceBinding>(FragmentSourceBinding::inflate) {

    private val viewModel: SourceViewModel by viewModels()

    private val dataCategories: CategoriesItem? by lazy {
        arguments?.parcelable("data")
    }

    private val navigation: NavController? by lazy {
        activity?.findNavController(R.id.nav_host_main)
    }

    override fun setupView(savedInstanceState: Bundle?) = with(binding) {
        toolbar.setToolbar(dataCategories?.title) {
            navigation?.navigateUp()
            etSearch.hideKeyboard()
        }
    }

    override fun setupViewModel() {
        viewModel.getSource(dataCategories?.id)
        viewModel.sources.observe(viewLifecycleOwner) {
            when(it) {
               is BaseResultState.Loading -> onLoading(true)
               is BaseResultState.Success -> onSuccess(it.data)
               is BaseResultState.Error -> onError(it.error)
            }
        }

        viewModel.setupSearchSource(binding.etSearch, dataCategories?.id)
        viewModel.search.observe(viewLifecycleOwner) {
            onSuccess(it)
        }
    }

    private fun onLoading(isLoading: Boolean) = with(binding){
        if (isLoading) {
            loading.isVisible = true
            uikitError.isVisible = false
        } else {
            loading.isVisible = false
        }
    }

    private fun onSuccess(dataList: List<SourceItem>) = with(binding){
        val adapter = SourceAdapter { goToNews(it) }
        rvSource.adapter = adapter
        rvSource.layoutManager = LinearLayoutManager(requireContext())
        adapter.setData(dataList)
        checkEmpty(dataList)
        onLoading(false)
        viewModel.setData(dataList.toMutableList())
    }

    private fun onError(error: Throwable) = with(binding){
        onLoading(false)
        uikitError.isVisible = true
        when(error) {
            is HttpException -> {
                uikitError.setError(
                    title = getString(R.string.title_error),
                    message = errorMapper(error)?.message
                ) {
                    viewModel.getSource(dataCategories?.id)
                }
            }

            is IOException -> {
                uikitError.setError(
                    title = getString(R.string.title_error_no_internet),
                    message = getString(R.string.title_error_no_internet_des)
                ) {
                    viewModel.getSource(dataCategories?.id)
                }
            }
        }
    }

    private fun checkEmpty(dataList: List<SourceItem>) = with(binding) {
        if (dataList.isEmpty()) tvEmptyData.show() else tvEmptyData.hide()
    }

    private fun goToNews(data: SourceItem) {
        navigation?.navigate(
            R.id.action_sourceFragment_to_newsFragment,
            bundleOf("data" to data)
        )
    }

}