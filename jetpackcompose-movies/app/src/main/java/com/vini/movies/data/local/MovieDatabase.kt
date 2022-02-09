package com.vini.movies.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vini.movies.data.local.dao.GenreDao
import com.vini.movies.data.local.dao.MovieDao
import com.vini.movies.data.local.dao.MovieRemoteKeyDao
import com.vini.movies.domain.model.Genre
import com.vini.movies.domain.model.Movie
import com.vini.movies.domain.model.MovieRemoteKeys

@Database(entities = [Movie::class, MovieRemoteKeys::class, Genre::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun movieRemoteKeysDao(): MovieRemoteKeyDao
    abstract fun movieGenreDao():GenreDao
}