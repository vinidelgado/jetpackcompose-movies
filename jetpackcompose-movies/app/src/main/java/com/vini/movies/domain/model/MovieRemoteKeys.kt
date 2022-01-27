package com.vini.movies.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vini.movies.util.Constants

@Entity(tableName = Constants.MOVIE_REMOTE_KEYS_DATABASE_TABLE)
data class MovieRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id:Int,
    val prevPage:Int?,
    val nextPage: Int?
)