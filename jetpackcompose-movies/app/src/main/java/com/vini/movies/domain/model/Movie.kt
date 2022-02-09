package com.vini.movies.domain.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.vini.movies.util.Constants.MOVIE_DATABASE_TABLE
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = MOVIE_DATABASE_TABLE)
data class Movie(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val posterPath: String? = "",
    val genres: String? = "",
    val adult: Boolean,
    val overview: String? = "",
    val releaseDate: String? = "",
    val originalTitle: String? = "",
    val originalLanguage: String? = "",
    val title: String? = "",
    val backdropPath: String? = "",
    val popularity: Float,
    val vote_count: Int,
    val video: Boolean,
    val voteAverage: Float,
)


