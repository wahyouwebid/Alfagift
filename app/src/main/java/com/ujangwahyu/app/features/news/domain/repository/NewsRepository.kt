package com.ujangwahyu.app.features.news.domain.repository

import androidx.paging.Pager
import com.ujangwahyu.app.features.news.domain.model.NewsItem

interface NewsRepository {

    fun getNews(sources: String): Pager<Int, NewsItem>
}