package com.ujangwahyu.app.features.news.data.di

import androidx.paging.PagingConfig
import com.ujangwahyu.app.features.news.data.api.NewsApiService
import com.ujangwahyu.app.features.news.data.datasource.NewsDataSource
import com.ujangwahyu.app.features.news.data.paging.NewsPagingSource
import com.ujangwahyu.app.features.news.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NewsDataModule {

    @Provides
    fun provideDatasource(
        pagingSource: NewsPagingSource,
        pagingConfig: PagingConfig,
    ): NewsRepository {
        return NewsDataSource(pagingSource, pagingConfig)
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): NewsApiService {
        return retrofit.create(NewsApiService::class.java)
    }

}