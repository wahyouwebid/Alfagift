package com.ujangwahyu.app.features.source.data.datasource

import com.ujangwahyu.app.features.source.data.api.SourceApiService
import com.ujangwahyu.app.features.source.domain.model.SourceItem
import com.ujangwahyu.app.features.source.domain.repository.SourceRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class SourceDataSource@Inject constructor(
    private val apiService: SourceApiService,
): SourceRepository {

    override fun getSources(categories: String): Observable<List<SourceItem>> {
        return apiService.getSource(categories).map { it.sources.map { data -> data.toDomain() }}
    }

}