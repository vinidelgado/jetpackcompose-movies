package com.vini.movies.domain.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vini.movies.domain.data.local.dao.MovieDao
import com.vini.movies.domain.data.local.dao.MovieRemoteKeyDao
import com.vini.movies.domain.model.Movie
import com.vini.movies.domain.model.MovieRemoteKey

@Database(entities = [Movie::class, MovieRemoteKey::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun movieRemoteKeyDao(): MovieRemoteKeyDao
}