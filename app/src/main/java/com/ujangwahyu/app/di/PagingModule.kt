package com.ujangwahyu.app.di

import androidx.paging.PagingConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PagingModule {

    @Provides
    @Singleton
    fun providePageConfig(): PagingConfig = PagingConfig(
        initialLoadSize = 20,
        pageSize = 10,
        enablePlaceholders = false,
        prefetchDistance = 1
    )
}