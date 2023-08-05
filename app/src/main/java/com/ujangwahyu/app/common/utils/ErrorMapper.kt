package com.ujangwahyu.app.common.utils

import com.google.gson.Gson
import com.ujangwahyu.app.core.model.ErrorResponse
import retrofit2.HttpException

object ErrorMapper {
    fun errorMapper(response: HttpException?): ErrorResponse? {
        val errorBody = response?.response()?.errorBody()?.string()
        return Gson().fromJson(errorBody, ErrorResponse::class.java)
    }
}