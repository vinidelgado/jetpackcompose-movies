package com.vini.movies.data.repository

import androidx.paging.PagingData
import com.vini.movies.domain.model.Movie
import com.vini.movies.domain.repository.DataStoreOperations
import com.vini.movies.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val remote: RemoteDataSource,
    private val dataStore: DataStoreOperations
) {

    fun getAllMovies(): Flow<PagingData<Movie>> {
        return remote.getAllMovies()
    }

    suspend fun saveOnboardingState(completed: Boolean) {
        dataStore.saveOnboardingViewState(completed = completed)
    }

    fun readOnboardingState(): Flow<Boolean> {
        return dataStore.readOnboardingViewState()
    }
}