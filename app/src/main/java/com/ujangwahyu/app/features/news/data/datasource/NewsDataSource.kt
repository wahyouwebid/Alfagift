package com.ujangwahyu.app.features.news.data.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.ujangwahyu.app.features.news.data.paging.NewsPagingSource
import com.ujangwahyu.app.features.news.domain.model.NewsItem
import com.ujangwahyu.app.features.news.domain.repository.NewsRepository
import javax.inject.Inject

class NewsDataSource @Inject constructor(
    private val pagingSource : NewsPagingSource,
    private val pagingConfig: PagingConfig,
): NewsRepository {

    override fun getNews(sources: String): Pager<Int, NewsItem> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                pagingSource.source = sources
                pagingSource
            }
        )
    }
}