package com.ujangwahyu.app.features.source.presentation.viewmodel

import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jakewharton.rxbinding3.widget.TextViewTextChangeEvent
import com.jakewharton.rxbinding3.widget.textChangeEvents
import com.ujangwahyu.app.common.base.BaseResultState
import com.ujangwahyu.app.features.source.domain.model.SourceItem
import com.ujangwahyu.app.features.source.domain.usecase.SourceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class SourceViewModel @Inject constructor(
    private val useCase: SourceUseCase
): ViewModel() {

    private var dataList = listOf<SourceItem>()

    val sources: MutableLiveData<BaseResultState<List<SourceItem>>> by lazy {
        MutableLiveData()
    }

    val search: MutableLiveData<List<SourceItem>> by lazy {
        MutableLiveData()
    }

    fun getSource(categories: String?) {
        useCase.getSources(categories ?: "") {
            sources.postValue(it)
        }
    }

    fun setData(data: List<SourceItem>) {
        dataList = data
    }

    fun setupSearchSource(editText: EditText, categories: String?){
        editText.textChangeEvents()
            .skipInitialValue()
            .debounce(500, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<TextViewTextChangeEvent>() {
                override fun onNext(t: TextViewTextChangeEvent) {
                    val keyword = t.text.toString()
                    if(keyword.trim{it <= ' '}.isNotEmpty() && keyword.trim { it <= ' ' }.isNotEmpty()) {
                        val data = dataList.filter { it.name!!.contains(keyword, ignoreCase = true) }
                        search.postValue(data)
                    } else {
                        getSource(categories)
                    }
                }

                override fun onError(e: Throwable) {

                }

                override fun onComplete() {

                }
            })
            .let { return@let useCase.getDisposable()::add }
    }
}