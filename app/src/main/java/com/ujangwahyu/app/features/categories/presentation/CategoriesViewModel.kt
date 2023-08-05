package com.ujangwahyu.app.features.categories.presentation

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ujangwahyu.app.features.categories.domain.model.CategoriesItem
import com.ujangwahyu.app.features.categories.domain.usecase.CategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val useCase: CategoriesUseCase
): ViewModel() {

    val categories: MutableLiveData<List<CategoriesItem>> by lazy {
        MutableLiveData()
    }

    fun getCategories(context: Context) {
        useCase.getCategories(context) {
            categories.postValue(it)
        }
    }
}