package com.vini.movies.di

import android.content.Context
import com.vini.movies.domain.pref.DataStoreOperationsImpl
import com.vini.movies.domain.repository.DataStoreOperations
import com.vini.movies.domain.repository.Repository
import com.vini.movies.domain.use_cases.UseCases
import com.vini.movies.domain.use_cases.read_onboarding.ReadOnboardingUseCase
import com.vini.movies.domain.use_cases.save_onboarding.SaveOnboardingUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideDataStoreOperations(@ApplicationContext context: Context): DataStoreOperations {
        return DataStoreOperationsImpl(context = context)
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: Repository): UseCases {
        return UseCases(
            saveOnboardingUseCase = SaveOnboardingUseCase(repository = repository),
            readOnboardingUseCase = ReadOnboardingUseCase(repository = repository)
        )
    }
}