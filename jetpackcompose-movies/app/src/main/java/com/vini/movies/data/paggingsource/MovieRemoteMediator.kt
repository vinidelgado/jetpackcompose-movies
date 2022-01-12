package com.vini.movies.data.paggingsource

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.vini.movies.data.local.MovieDatabase
import com.vini.movies.data.remote.TmdbApi
import com.vini.movies.domain.model.Movie
import com.vini.movies.domain.model.MovieRemoteKeys
import java.lang.Exception
import javax.inject.Inject

@ExperimentalPagingApi
class MovieRemoteMediator @Inject constructor(
    private val movieApi: TmdbApi,
    private val movieDatabase: MovieDatabase
) : RemoteMediator<Int, Movie>() {

    private val movieDao = movieDatabase.movieDao()
    private val movieRemoteKeysDao = movieDatabase.movieRemoteKeysDao()

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Movie>): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }
            val response = movieApi.getLatestMovies(
                apiKey = "531e23ae1335c830faeef060e80f9078",
                page = page,
                language = "en-US",
                region = null
            )
            if (response.results.isNotEmpty()) {
                movieDatabase.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        movieDao.deleteAllMovies()
                        movieRemoteKeysDao.deleteAllRemoteKeys()
                    }
                    val prevPage = response.page
                    val nextPage = if (prevPage < response.total_pages - 1) prevPage + 1 else -1
                    val keys = response.results.map {
                        MovieRemoteKeys(
                            id = it.id,
                            prevPage = prevPage,
                            nextPage = nextPage
                        )
                    }
                    movieRemoteKeysDao.addAllRemoteKeys(keys)
                    movieDao.addMovie(movies = response.results)
                }
            }
            val endPaginationReached = response.page >= response.total_pages - 1
            MediatorResult.Success(endOfPaginationReached = endPaginationReached)
        } catch (exception: Exception) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, Movie>): MovieRemoteKeys? {
        return state.pages.firstOrNull {
            it.data.isNullOrEmpty()
        }?.data?.firstOrNull()?.let { movie ->
            movieRemoteKeysDao.getRemoteKeys(movieId = movie.id)
        }
    }

    private suspend fun getRemoteKeyClosestCurrentPosition(state: PagingState<Int, Movie>): MovieRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let {
                movieRemoteKeysDao.getRemoteKeys(movieId = it)
            }
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Movie>): MovieRemoteKeys? {
        return state.pages.lastOrNull() {
            it.data.isNullOrEmpty()
        }?.data?.lastOrNull()?.let { movie ->
            movieRemoteKeysDao.getRemoteKeys(movieId = movie.id)
        }
    }
}