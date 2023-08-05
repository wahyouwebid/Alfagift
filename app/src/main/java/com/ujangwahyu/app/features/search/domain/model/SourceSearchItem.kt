package com.ujangwahyu.app.features.search.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SourceSearchItem(
    @SerializedName("id")
    val id: String? = "",

    @SerializedName("name")
    val name: String? = ""
) : Parcelable

