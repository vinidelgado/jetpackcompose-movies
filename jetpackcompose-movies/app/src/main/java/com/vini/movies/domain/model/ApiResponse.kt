package com.vini.movies.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val page: Int,
    val results:List<Movie> = emptyList(),
    val total_results:Int = 0,
    val total_pages: Int = 0,
)