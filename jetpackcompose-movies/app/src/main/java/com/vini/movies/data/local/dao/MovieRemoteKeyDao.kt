package com.vini.movies.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vini.movies.domain.model.MovieRemoteKeys

@Dao
interface MovieRemoteKeyDao {
    @Query("SELECT * FROM movie_remote_keys_table where id=:movieId")
    suspend fun getRemoteKeys(movieId:Int): MovieRemoteKeys?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(movies:List<MovieRemoteKeys>)

    @Query("DELETE FROM movie_remote_keys_table")
    suspend fun deleteAllRemoteKeys()
}