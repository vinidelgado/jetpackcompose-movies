package com.vini.movies.data.repository

import com.vini.movies.data.local.MovieDatabase
import com.vini.movies.domain.model.Genre
import com.vini.movies.domain.model.Movie
import com.vini.movies.domain.repository.LocalDataSource

class LocalDataSourceImpl(movieDatabase: MovieDatabase) : LocalDataSource {

    private val movieDao = movieDatabase.movieDao()
    private val genreDao = movieDatabase.movieGenreDao()

    override suspend fun getSelectedMovie(movieId: Int): Movie {
        return movieDao.getSelectedMovie(movieId = movieId)
    }

    override suspend fun saveMovieGenres(list: List<Genre>) {
        genreDao.addGenre(list)
    }
}