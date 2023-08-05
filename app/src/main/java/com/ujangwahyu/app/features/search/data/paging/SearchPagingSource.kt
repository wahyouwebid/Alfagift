package com.ujangwahyu.app.features.search.data.paging

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.ujangwahyu.app.core.model.ResponseNews
import com.ujangwahyu.app.features.search.data.api.SearchApiService
import com.ujangwahyu.app.features.search.domain.model.SearchItem
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class SearchPagingSource @Inject constructor(
    private val apiService: SearchApiService
) : RxPagingSource<Int, SearchItem>() {

    lateinit var query: String

    override fun getRefreshKey(state: PagingState<Int, SearchItem>): Int? {
        return state.anchorPosition
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, SearchItem>> {
        val page = params.key ?: 1
        return apiService.searchNews(query, page, params.loadSize)
            .subscribeOn(Schedulers.io())
            .map { toLoadResult(it, page) }
            .onErrorReturn { LoadResult.Error(it) }
    }

    private fun toLoadResult(data: ResponseNews, page: Int): LoadResult<Int, SearchItem> {
        return LoadResult.Page(
            data = data.data.map { it.toSearchDomain() },
            prevKey = if (page == 1) null else page - 1,
            nextKey = if (page == data.totalResults || data.data.isEmpty()) null else page + 1
        )
    }
}