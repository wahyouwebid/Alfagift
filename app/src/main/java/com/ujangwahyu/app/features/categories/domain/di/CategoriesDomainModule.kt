package com.ujangwahyu.app.features.categories.domain.di

import com.ujangwahyu.app.features.categories.domain.interactor.CategoriesInteractor
import com.ujangwahyu.app.features.categories.domain.repository.CategoriesRepository
import com.ujangwahyu.app.features.categories.domain.usecase.CategoriesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.disposables.CompositeDisposable

@InstallIn(SingletonComponent::class)
@Module
class CategoriesDomainModule {

    @Provides
    fun provideInteractor(
        repository: CategoriesRepository,
        disposable: CompositeDisposable
    ): CategoriesUseCase {
        return CategoriesInteractor(repository, disposable)
    }

}