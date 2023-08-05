package com.ujangwahyu.app.features.source.data.api

import com.ujangwahyu.app.features.source.data.model.ResponseSource
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface SourceApiService {

    @GET("sources")
    fun getSource(
        @Query("category") category: String?
    ): Observable<ResponseSource>

}