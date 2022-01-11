package com.vini.movies.data.remote

import com.vini.movies.domain.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbApi {
    @GET("/3/movie/top_rated")
    suspend fun getLatestMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String?,
        @Query("page") page: Int = 1,
        @Query("region") region: String?,
    ): ApiResponse
}