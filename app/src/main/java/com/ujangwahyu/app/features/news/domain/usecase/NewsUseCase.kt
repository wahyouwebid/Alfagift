package com.ujangwahyu.app.features.news.domain.usecase

import androidx.paging.PagingData
import com.ujangwahyu.app.features.news.domain.model.NewsItem
import kotlinx.coroutines.CoroutineScope

interface NewsUseCase {

    fun getNews(
        sources : String,
        scope: CoroutineScope,
        callback: (PagingData<NewsItem>) -> Unit
    )

}