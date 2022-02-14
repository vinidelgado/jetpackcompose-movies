package com.vini.movies.domain.repository

import com.vini.movies.domain.model.Genre
import com.vini.movies.domain.model.Movie

interface LocalDataSource {
    suspend fun getSelectedMovie(movieId: Int): Movie
    suspend fun saveMovieGenres(list:List<Genre>)
    suspend fun getMovieGenres(list:List<Int>):List<Genre>
}