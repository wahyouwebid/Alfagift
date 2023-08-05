package com.ujangwahyu.app.features.source.domain.repository

import com.ujangwahyu.app.features.source.domain.model.SourceItem
import io.reactivex.rxjava3.core.Observable

interface SourceRepository {

    fun getSources(categories: String): Observable<List<SourceItem>>

}