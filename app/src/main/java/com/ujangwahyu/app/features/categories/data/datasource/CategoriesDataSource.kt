package com.ujangwahyu.app.features.categories.data.datasource

import android.content.Context
import com.google.gson.Gson
import com.ujangwahyu.app.common.utils.loadJSONFromAssets
import com.ujangwahyu.app.features.categories.data.model.DataCategories
import com.ujangwahyu.app.features.categories.domain.model.CategoriesItem
import com.ujangwahyu.app.features.categories.domain.repository.CategoriesRepository
import io.reactivex.rxjava3.core.Single

class CategoriesDataSource: CategoriesRepository {

    override fun getCategories(context: Context): Single<List<CategoriesItem>> {
        val jsonArray = context.loadJSONFromAssets("categories.json")
        val data = Gson().fromJson(jsonArray, Array<DataCategories>::class.java).toList()
        return Single.just(data.map { it.mapToDomain() })
    }

}