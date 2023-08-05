package com.ujangwahyu.app.features.news.data.paging

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.ujangwahyu.app.core.model.ResponseNews
import com.ujangwahyu.app.features.news.data.api.NewsApiService
import com.ujangwahyu.app.features.news.domain.model.NewsItem
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class NewsPagingSource @Inject constructor(
    private val apiService: NewsApiService
): RxPagingSource<Int, NewsItem>() {

    lateinit var source: String

    override fun getRefreshKey(state: PagingState<Int, NewsItem>): Int? {
        return state.anchorPosition
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, NewsItem>> {
        val page = params.key ?: 1
        return apiService.getNews(source, page, params.loadSize)
            .subscribeOn(Schedulers.io())
            .map { toLoadResult(it, page) }
            .onErrorReturn { LoadResult.Error(it) }
    }

    private fun toLoadResult(data: ResponseNews, page: Int): LoadResult<Int, NewsItem> {
        return LoadResult.Page(
            data = data.data.map { it.toNewsDomain() },
            prevKey = if (page == 1) null else page - 1,
            nextKey = if (page == data.totalResults || data.data.isEmpty()) null else page + 1
        )
    }

}