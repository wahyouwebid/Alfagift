package com.ujangwahyu.app.features.categories.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoriesItem(
    var id: String?,
    var title: String?,
    var icon: String?,
    var color: String?
) : Parcelable
