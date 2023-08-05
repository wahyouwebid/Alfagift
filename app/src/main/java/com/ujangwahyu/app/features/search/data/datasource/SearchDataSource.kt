package com.ujangwahyu.app.features.search.data.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.ujangwahyu.app.features.search.data.paging.SearchPagingSource
import com.ujangwahyu.app.features.search.domain.model.SearchItem
import com.ujangwahyu.app.features.search.domain.repository.SearchRepository
import javax.inject.Inject

class SearchDataSource @Inject constructor(
    private val pagingSource : SearchPagingSource,
    private val pagingConfig: PagingConfig,
): SearchRepository {

    override fun searchNews(query: String): Pager<Int, SearchItem> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                pagingSource.query = query
                pagingSource
            }
        )
    }
}