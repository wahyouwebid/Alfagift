package com.ujangwahyu.app.features.detail.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.ujangwahyu.app.features.news.domain.model.NewsItem
import com.ujangwahyu.app.features.news.domain.model.SourceItem
import com.ujangwahyu.app.features.search.domain.model.SearchItem
import com.ujangwahyu.app.features.search.domain.model.SourceSearchItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataDetail(
    var url: String?,
    var title: String?,
): Parcelable