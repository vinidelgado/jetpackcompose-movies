package com.vini.movies.domain.repository

import androidx.paging.PagingData
import com.vini.movies.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getAllMovies(): Flow<PagingData<Movie>>
}