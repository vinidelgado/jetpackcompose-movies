package com.vini.movies.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vini.movies.util.Constants.GENRE_DATABASE_TABLE
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = GENRE_DATABASE_TABLE)
data class Genre(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name:String?=""
)


