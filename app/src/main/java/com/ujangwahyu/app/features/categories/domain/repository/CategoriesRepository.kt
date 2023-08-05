package com.ujangwahyu.app.features.categories.domain.repository

import android.content.Context
import com.ujangwahyu.app.features.categories.domain.model.CategoriesItem
import io.reactivex.rxjava3.core.Single

interface CategoriesRepository {
    fun getCategories(
        context : Context,
    ): Single<List<CategoriesItem>>
}