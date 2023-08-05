package com.ujangwahyu.app.features.source.domain.interactor

import com.ujangwahyu.app.common.base.BaseResultState
import com.ujangwahyu.app.features.source.domain.model.SourceItem
import com.ujangwahyu.app.features.source.domain.repository.SourceRepository
import com.ujangwahyu.app.features.source.domain.usecase.SourceUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class SourceInteractor@Inject constructor(
    private val repository: SourceRepository,
    private val disposable: CompositeDisposable
): SourceUseCase {

    override fun getSources(
        categories: String,
        callback: (BaseResultState<List<SourceItem>>) -> Unit
    ) {
        repository.getSources(categories)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map<BaseResultState<List<SourceItem>>> { BaseResultState.Success(it) }
            .onErrorReturn { BaseResultState.Error(it) }
            .startWithItem(BaseResultState.Loading)
            .subscribe(callback)
            .let { disposable.add(it) }
    }

    override fun getDisposable(): CompositeDisposable {
        return disposable
    }

}