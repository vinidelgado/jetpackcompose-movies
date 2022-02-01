package com.vini.movies.domain.repository

import com.vini.movies.domain.model.Movie

interface LocalDataSource {
    suspend fun getSelectedMovie(movieId: Int): Movie
}