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
    val adult: Boolean,
    val backdrop_path: String,
    val original_title: String,
    val popularity: Float,
    val poster_path: String,
    val title: String,
)