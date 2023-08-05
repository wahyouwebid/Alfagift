package com.ujangwahyu.app.features.source.domain.usecase

import com.ujangwahyu.app.common.base.BaseResultState
import com.ujangwahyu.app.features.source.domain.model.SourceItem
import io.reactivex.rxjava3.disposables.CompositeDisposable

interface SourceUseCase {

    fun getSources(
        categories: String,
        callback : (BaseResultState<List<SourceItem>>) -> Unit
    )

    fun getDisposable(): CompositeDisposable
}