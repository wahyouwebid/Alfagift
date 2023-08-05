package com.ujangwahyu.app.features.categories.data.model

import android.os.Parcelable
import com.ujangwahyu.app.features.categories.domain.model.CategoriesItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataCategories(
    var id: String?,
    var title: String?,
    var icon: String?,
    var color: String?
) : Parcelable {
    fun mapToDomain(): CategoriesItem {
        return CategoriesItem(
            id = this.id,
            title = this.title,
            icon = this.icon,
            color = this.color
        )
    }
}
