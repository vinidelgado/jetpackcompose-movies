package com.vini.movies.domain.use_cases.all_genres

import com.vini.movies.data.repository.Repository
import com.vini.movies.domain.model.Genre

class GetAllMovieGenresUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(list:List<Int>): List<Genre> {
        return repository.getMovieGenres(list)
    }
}