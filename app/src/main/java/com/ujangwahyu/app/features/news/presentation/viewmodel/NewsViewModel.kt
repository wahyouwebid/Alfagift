package com.ujangwahyu.app.features.news.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.ujangwahyu.app.features.news.domain.model.NewsItem
import com.ujangwahyu.app.features.news.domain.usecase.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val useCase: NewsUseCase
): ViewModel() {

    val news: MutableLiveData<PagingData<NewsItem>> by lazy {
        MutableLiveData()
    }

    fun getNews(source: String?) {
        useCase.getNews(source ?: "", viewModelScope) {
            news.postValue(it)
        }
    }

}