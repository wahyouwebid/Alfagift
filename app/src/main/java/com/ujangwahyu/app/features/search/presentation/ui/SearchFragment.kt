package com.ujangwahyu.app.features.search.presentation.ui

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.ujangwahyu.app.R
import com.ujangwahyu.app.common.base.BaseFragment
import com.ujangwahyu.app.common.utils.ErrorMapper
import com.ujangwahyu.app.common.utils.showKeyboard
import com.ujangwahyu.app.core.mapper.DataMapper.toDataDetail
import com.ujangwahyu.app.core.paging.FooterAdapter
import com.ujangwahyu.app.databinding.FragmentSearchBinding
import com.ujangwahyu.app.features.search.domain.model.SearchItem
import com.ujangwahyu.app.features.search.presentation.adapter.SearchAdapter
import com.ujangwahyu.app.features.search.presentation.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.HttpException
import java.io.IOException


@AndroidEntryPoint
class SearchFragment: BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    private val viewModel : SearchViewModel by viewModels()

    private val navigation: NavController? by lazy {
        activity?.findNavController(R.id.nav_host_main)
    }

    private val adapter : SearchAdapter by lazy {
        SearchAdapter { item -> goToDetail(item)}
    }

    override fun setupView(savedInstanceState: Bundle?) = with(binding) {
        rvNews.adapter = adapter.withLoadStateFooter(FooterAdapter { adapter.retry() })
        rvNews.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvNews.setHasFixedSize(true)
        adapter.addLoadStateListener { loadState(it) }

        etSearch.requestFocus()
        etSearch.showKeyboard()

        ivBack.setOnClickListener {
            navigation?.navigateUp()
        }
    }

    override fun setupViewModel() {
        viewModel.setupSearchNews(binding.etSearch)
        viewModel.search.observe(viewLifecycleOwner) { result ->
            adapter.submitData(lifecycle, result)
        }
    }

    private fun loadState(loadState: CombinedLoadStates) {
        when(loadState.source.refresh) {
            is LoadState.Loading -> onLoading()
            is LoadState.Error -> onError(loadState)
            else -> onCheckEmpty(loadState)
        }
    }

    private fun onCheckEmpty(loadState: CombinedLoadStates) {
        with(binding) {
            if (loadState.source.refresh is LoadState.NotLoading &&
                loadState.append.endOfPaginationReached &&
                adapter.itemCount < 1
            ) {
                loading.isVisible = false
                rvNews.isVisible = false
                uikitError.isVisible = false
                tvEmptyData.isVisible = true
            } else {
                loading.isVisible = false
                rvNews.isVisible = true
                uikitError.isVisible = false
                tvEmptyData.isVisible = false
            }
        }
    }
    private fun onLoading() = with(binding){
        loading.isVisible = true
        rvNews.isVisible = false
        uikitError.isVisible = false
        tvEmptyData.isVisible = false
    }

    private fun onError(loadState: CombinedLoadStates) {
        with(binding) {
            when(val error = (loadState.source.refresh as LoadState.Error).error) {
                is HttpException -> {
                    uikitError.setError(
                        title = getString(R.string.title_error),
                        message = ErrorMapper.errorMapper(error)?.message
                    ) {
                        adapter.retry()
                    }
                }

                is IOException -> {
                    uikitError.setError(
                        title = getString(R.string.title_error_no_internet),
                        message = getString(R.string.title_error_no_internet_des)
                    ) {
                        adapter.retry()
                    }
                }
            }

            loading.isVisible = false
            rvNews.isVisible = false
            uikitError.isVisible = true
            tvEmptyData.isVisible = false
        }
    }

    private fun goToDetail(data: SearchItem?) {
        navigation?.navigate(
            R.id.action_searchFragment_to_detailFragment,
            bundleOf("data" to data?.toDataDetail())
        )
    }
}