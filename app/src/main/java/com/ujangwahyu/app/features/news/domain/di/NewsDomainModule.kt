package com.ujangwahyu.app.features.news.domain.di

import com.ujangwahyu.app.features.news.domain.interactor.NewsInteractor
import com.ujangwahyu.app.features.news.domain.repository.NewsRepository
import com.ujangwahyu.app.features.news.domain.usecase.NewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.disposables.CompositeDisposable

@InstallIn(SingletonComponent::class)
@Module
class NewsDomainModule {

    @Provides
    fun provideInteractor(
        repository: NewsRepository,
        disposable: CompositeDisposable
    ): NewsUseCase {
        return NewsInteractor(repository, disposable)
    }

}