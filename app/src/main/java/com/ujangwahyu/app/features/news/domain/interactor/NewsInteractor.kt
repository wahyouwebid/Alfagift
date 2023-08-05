package com.ujangwahyu.app.features.news.domain.interactor

import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import androidx.paging.rxjava3.flowable
import com.ujangwahyu.app.features.news.domain.model.NewsItem
import com.ujangwahyu.app.features.news.domain.repository.NewsRepository
import com.ujangwahyu.app.features.news.domain.usecase.NewsUseCase
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class NewsInteractor @Inject constructor(
    private val repository: NewsRepository,
    private val disposable: CompositeDisposable
) : NewsUseCase{

    override fun getNews(
        sources: String,
        scope: CoroutineScope,
        callback: (PagingData<NewsItem>) -> Unit
    ) {
        repository.getNews(sources).flowable
            .cachedIn(scope)
            .subscribe(callback)
            .let { return@let disposable::add}
    }

}