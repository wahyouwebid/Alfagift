package com.ujangwahyu.app.core.model

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("message")
    val message: String?,

    @SerializedName("status")
    val documentationUrl: String?,

    @SerializedName("code")
    val code: String?,
)
