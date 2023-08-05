package com.ujangwahyu.app.features.source.domain.di

import com.ujangwahyu.app.features.source.domain.interactor.SourceInteractor
import com.ujangwahyu.app.features.source.domain.repository.SourceRepository
import com.ujangwahyu.app.features.source.domain.usecase.SourceUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.disposables.CompositeDisposable

@InstallIn(SingletonComponent::class)
@Module
class SourceDomainModule {

    @Provides
    fun provideInteractor(
        repository: SourceRepository,
        disposable: CompositeDisposable
    ): SourceUseCase {
        return SourceInteractor(repository, disposable)
    }

}