package com.vini.movies.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val page: Int,
    val results:List<Movie> = emptyList(),
    val totalResults:Int = 0,
    val totalPages: Int = 0,
)