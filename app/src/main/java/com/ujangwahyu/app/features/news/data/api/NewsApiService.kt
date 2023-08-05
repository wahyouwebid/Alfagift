package com.ujangwahyu.app.features.news.data.api

import com.ujangwahyu.app.core.model.ResponseNews
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("top-headlines")
    fun getNews(
        @Query("sources") sources: String?,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
    ): Single<ResponseNews>

}