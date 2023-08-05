package com.ujangwahyu.app.features.search.data.api

import com.ujangwahyu.app.core.model.ResponseNews
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApiService {

    @GET("everything")
    fun searchNews(
        @Query("q") keyword: String?,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
    ): Single<ResponseNews>

}