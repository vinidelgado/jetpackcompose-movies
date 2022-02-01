package com.vini.movies.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.vini.movies.data.local.MovieDatabase
import com.vini.movies.data.paggingsource.MovieLatestRemoteMediator
import com.vini.movies.data.remote.TmdbApi
import com.vini.movies.domain.model.Movie
import com.vini.movies.domain.repository.RemoteDataSource
import com.vini.movies.util.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
class RemoteDataSourceImpl(
    private val tmdbApi: TmdbApi,
    private val movieDatabase: MovieDatabase
):RemoteDataSource{

    private val movieDao = movieDatabase.movieDao()

    override fun getLatestMovies(): Flow<PagingData<Movie>> {
        val pagingSourceFactory = {movieDao.getAllMovies()}
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = MovieLatestRemoteMediator(
                movieApi = tmdbApi,
                movieDatabase = movieDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow

    }
}