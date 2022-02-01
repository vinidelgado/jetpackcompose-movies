package com.vini.movies.data.repository

import com.vini.movies.data.local.MovieDatabase
import com.vini.movies.domain.model.Movie
import com.vini.movies.domain.repository.LocalDataSource

class LocalDataSourceImpl(movieDatabase: MovieDatabase) : LocalDataSource {

    private val movieDao = movieDatabase.movieDao()

    override suspend fun getSelectedMovie(movieId: Int): Movie {
        return movieDao.getSelectedMovie(movieId = movieId)
    }
}