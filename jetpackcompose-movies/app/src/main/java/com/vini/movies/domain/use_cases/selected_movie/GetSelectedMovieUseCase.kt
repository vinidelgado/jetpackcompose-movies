package com.vini.movies.domain.use_cases.selected_movie

import com.vini.movies.data.repository.Repository
import com.vini.movies.domain.model.Movie

class GetSelectedMovieUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(movieId: Int): Movie {
        return repository.getSelectedMovie(movieId = movieId)
    }
}