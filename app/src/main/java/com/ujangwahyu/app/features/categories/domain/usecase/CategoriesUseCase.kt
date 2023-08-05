package com.ujangwahyu.app.features.categories.domain.usecase

import android.content.Context
import com.ujangwahyu.app.features.categories.domain.model.CategoriesItem

interface CategoriesUseCase {

    fun getCategories(
        context : Context,
        callback: (List<CategoriesItem>) -> Unit
    )
}