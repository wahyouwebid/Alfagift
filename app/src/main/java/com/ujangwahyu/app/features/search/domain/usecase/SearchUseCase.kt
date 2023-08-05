package com.ujangwahyu.app.features.search.domain.usecase

import androidx.paging.PagingData
import com.ujangwahyu.app.features.search.domain.model.SearchItem
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineScope

interface SearchUseCase {

    fun searchNews(
        query: String,
        scope: CoroutineScope,
        callback: (PagingData<SearchItem>) -> Unit
    )

    fun getDisposable(): CompositeDisposable
}