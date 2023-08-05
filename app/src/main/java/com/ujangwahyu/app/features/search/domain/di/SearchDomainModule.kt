package com.ujangwahyu.app.features.search.domain.di

import com.ujangwahyu.app.features.search.domain.interactor.SearchInteractor
import com.ujangwahyu.app.features.search.domain.repository.SearchRepository
import com.ujangwahyu.app.features.search.domain.usecase.SearchUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.disposables.CompositeDisposable

@InstallIn(SingletonComponent::class)
@Module
class SearchDomainModule {

    @Provides
    fun provideInteractor(
        repository: SearchRepository,
        disposable: CompositeDisposable
    ): SearchUseCase {
        return SearchInteractor(repository, disposable)
    }

}