package com.ujangwahyu.app.features.categories.domain.interactor

import android.content.Context
import android.util.Log
import com.ujangwahyu.app.features.categories.domain.model.CategoriesItem
import com.ujangwahyu.app.features.categories.domain.repository.CategoriesRepository
import com.ujangwahyu.app.features.categories.domain.usecase.CategoriesUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class CategoriesInteractor @Inject constructor(
    private val repository: CategoriesRepository,
    private val disposable: CompositeDisposable
): CategoriesUseCase {
    override fun getCategories(context: Context, callback: (List<CategoriesItem>) -> Unit) {
        repository.getCategories(context)
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { data -> callback(data) },
                { Log.e("categories",it.message.toString()) }
            )
            .let { return@let disposable::add}
    }
}