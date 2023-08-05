package com.ujangwahyu.app.core.model

import com.google.gson.annotations.SerializedName

data class ResponseNews(
    @SerializedName("status")
    val status: String = "",

    @SerializedName("totalResults")
    val totalResults: Int = 0,

    @SerializedName("articles")
    val data: List<DataNews> = emptyList()
)