package com.ujangwahyu.app.features.search.data.di

import androidx.paging.PagingConfig
import com.ujangwahyu.app.features.search.data.api.SearchApiService
import com.ujangwahyu.app.features.search.data.datasource.SearchDataSource
import com.ujangwahyu.app.features.search.data.paging.SearchPagingSource
import com.ujangwahyu.app.features.search.domain.repository.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class SearchDataModule {

    @Provides
    fun provideDatasource(
        pagingSource: SearchPagingSource,
        pagingConfig: PagingConfig,
    ): SearchRepository {
        return SearchDataSource(pagingSource, pagingConfig)
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): SearchApiService {
        return retrofit.create(SearchApiService::class.java)
    }

}