package com.vini.movies.domain.use_cases.save_genre

import com.vini.movies.data.repository.Repository
import com.vini.movies.domain.model.Genre

class SaveMovieGenreUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(genreList: List<Genre>){
        repository.saveMovieGenre(genres = genreList)
    }
}