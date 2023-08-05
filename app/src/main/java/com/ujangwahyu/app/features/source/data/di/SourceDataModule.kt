package com.ujangwahyu.app.features.source.data.di

import com.ujangwahyu.app.features.source.data.api.SourceApiService
import com.ujangwahyu.app.features.source.data.datasource.SourceDataSource
import com.ujangwahyu.app.features.source.domain.repository.SourceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class SourceDataModule {

    @Provides
    fun provideDatasource(
        apiService: SourceApiService
    ): SourceRepository {
        return SourceDataSource(apiService)
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): SourceApiService {
        return retrofit.create(SourceApiService::class.java)
    }

}