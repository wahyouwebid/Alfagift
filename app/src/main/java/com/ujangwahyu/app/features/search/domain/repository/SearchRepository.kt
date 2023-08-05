package com.ujangwahyu.app.features.search.domain.repository

import androidx.paging.Pager
import com.ujangwahyu.app.features.search.domain.model.SearchItem

interface SearchRepository {

    fun searchNews(query: String): Pager<Int, SearchItem>

}