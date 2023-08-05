package com.ujangwahyu.app.features.search.domain.interactor

import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import androidx.paging.rxjava3.flowable
import com.ujangwahyu.app.features.search.domain.model.SearchItem
import com.ujangwahyu.app.features.search.domain.repository.SearchRepository
import com.ujangwahyu.app.features.search.domain.usecase.SearchUseCase
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class SearchInteractor @Inject constructor(
    private val repository: SearchRepository,
    private val disposable: CompositeDisposable
) : SearchUseCase{

    override fun searchNews(
        query: String,
        scope: CoroutineScope,
        callback: (PagingData<SearchItem>) -> Unit
    ) {
        repository.searchNews(query).flowable
            .cachedIn(scope)
            .subscribe(callback)
            .let { return@let disposable::add}
    }

    override fun getDisposable(): CompositeDisposable {
        return disposable
    }

}