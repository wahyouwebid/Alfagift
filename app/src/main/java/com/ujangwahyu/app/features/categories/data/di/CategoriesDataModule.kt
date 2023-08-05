package com.ujangwahyu.app.features.categories.data.di

import com.ujangwahyu.app.features.categories.data.datasource.CategoriesDataSource
import com.ujangwahyu.app.features.categories.domain.repository.CategoriesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class CategoriesDataModule {

    @Provides
    fun provideDatasource(): CategoriesRepository {
        return CategoriesDataSource()
    }

}