package com.vini.movies.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vini.movies.data.local.dao.MovieDao
import com.vini.movies.data.local.dao.MovieRemoteKeyDao
import com.vini.movies.domain.model.GenresTypeConverters
import com.vini.movies.domain.model.Movie
import com.vini.movies.domain.model.MovieRemoteKeys

@Database(entities = [Movie::class, MovieRemoteKeys::class], version = 1)
@TypeConverters(GenresTypeConverters::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun movieRemoteKeysDao(): MovieRemoteKeyDao
}