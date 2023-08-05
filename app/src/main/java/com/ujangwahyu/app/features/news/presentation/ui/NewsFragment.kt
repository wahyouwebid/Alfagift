package com.ujangwahyu.app.features.news.presentation.ui

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
import com.ujangwahyu.app.common.utils.ErrorMapper.errorMapper
import com.ujangwahyu.app.common.utils.parcelable
import com.ujangwahyu.app.core.mapper.DataMapper.toDataDetail
import com.ujangwahyu.app.core.paging.FooterAdapter
import com.ujangwahyu.app.databinding.FragmentNewsBinding
import com.ujangwahyu.app.features.news.domain.model.NewsItem
import com.ujangwahyu.app.features.news.presentation.adapter.NewsAdapter
import com.ujangwahyu.app.features.news.presentation.viewmodel.NewsViewModel
import com.ujangwahyu.app.features.source.domain.model.SourceItem
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.HttpException
import java.io.IOException

@AndroidEntryPoint
class NewsFragment: BaseFragment<FragmentNewsBinding>(FragmentNewsBinding::inflate) {

    private val viewModel: NewsViewModel by viewModels()

    private val navigation: NavController? by lazy {
        activity?.findNavController(R.id.nav_host_main)
    }

    private val dataSource: SourceItem? by lazy {
        arguments?.parcelable("data")
    }

    private val adapter : NewsAdapter by lazy {
        NewsAdapter { goToDetail(it) }
    }
    override fun setupView(savedInstanceState: Bundle?) = with(binding){
        rvNews.adapter = adapter.withLoadStateFooter(FooterAdapter { adapter.retry() })
        rvNews.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvNews.setHasFixedSize(true)
        adapter.addLoadStateListener { loadState(it) }

        toolbar.setToolbar(dataSource?.name) {
            navigation?.navigateUp()
        }

        search.setOnClickListener {
            goToSearch()
        }
    }

    override fun setupViewModel() {
        viewModel.getNews(dataSource?.id)
        viewModel.news.observe(viewLifecycleOwner) { result ->
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
                uikitError.isVisible = true
            } else {
                loading.isVisible = false
                rvNews.isVisible = true
                uikitError.isVisible = false
            }
        }
    }

    private fun onLoading() = with(binding){
        loading.isVisible = true
        rvNews.isVisible = false
        uikitError.isVisible = false
    }

    private fun onError(loadState: CombinedLoadStates) {
        with(binding) {
            when(val error = (loadState.source.refresh as LoadState.Error).error) {
                is HttpException -> {
                    uikitError.setError(
                        title = getString(R.string.title_error),
                        message = errorMapper(error)?.message
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

        }
    }

    private fun goToDetail(data: NewsItem?) {
        navigation?.navigate(
            R.id.action_newsFragment_to_detailFragment,
            bundleOf("data" to data?.toDataDetail())
        )
    }

    private fun goToSearch() {
        navigation?.navigate(
            R.id.action_newsFragment_to_searchFragment,
        )
    }
}