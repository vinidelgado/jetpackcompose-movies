package com.vini.movies.data.repository

import androidx.paging.PagingData
import com.vini.movies.domain.model.Genre
import com.vini.movies.domain.model.Movie
import com.vini.movies.domain.repository.DataStoreOperations
import com.vini.movies.domain.repository.LocalDataSource
import com.vini.movies.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val local: LocalDataSource,
    private val remote: RemoteDataSource,
    private val dataStore: DataStoreOperations
) {

    fun getLatestMovies(): Flow<PagingData<Movie>> {
        return remote.getLatestMovies()
    }

    suspend fun saveOnboardingState(completed: Boolean) {
        dataStore.saveOnboardingViewState(completed = completed)
    }

    fun readOnboardingState(): Flow<Boolean> {
        return dataStore.readOnboardingViewState()
    }

    suspend fun getSelectedMovie(movieId: Int): Movie {
        return local.getSelectedMovie(movieId = movieId)
    }

    suspend fun saveMovieGenre(genres:List<Genre>){
        local.saveMovieGenres(list = genres)
    }
}