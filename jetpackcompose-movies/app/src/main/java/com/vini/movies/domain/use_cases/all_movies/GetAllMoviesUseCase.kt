package com.vini.movies.domain.use_cases.all_movies

import androidx.paging.PagingData
import com.vini.movies.data.repository.Repository
import com.vini.movies.domain.model.Movie
import kotlinx.coroutines.flow.Flow

class GetAllMoviesUseCase(
    private val repository: Repository
) {
    operator fun invoke(): Flow<PagingData<Movie>> {
        return repository.getAllMovies()
    }
}