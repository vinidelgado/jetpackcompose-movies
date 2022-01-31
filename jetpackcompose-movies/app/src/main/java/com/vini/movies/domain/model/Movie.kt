package com.vini.movies.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vini.movies.util.Constants.MOVIE_DATABASE_TABLE
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = MOVIE_DATABASE_TABLE)
data class Movie(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val poster_path: String? = "",
    val adult: Boolean,
    val overview: String? = "",
    val release_date: String? = "",
    val original_title: String? = "",
    val original_language: String? = "",
    val title: String? = "",
    val backdrop_path: String? = "",
    val popularity: Float,
    val vote_count: Int,
    val video: Boolean,
    val vote_average: Float,
)


