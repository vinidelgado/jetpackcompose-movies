package com.vini.movies.domain.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vini.movies.domain.model.Movie
import com.vini.movies.domain.model.MovieRemoteKey

@Dao
interface MovieRemoteKeyDao {
    @Query("SELECT * FROM movie_remote_key_table where id=:id")
    suspend fun getRemoteKey(id:Int): MovieRemoteKey?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKey(movies:List<MovieRemoteKey>)

    @Query("DELETE FROM movie_remote_key_table")
    suspend fun deleteAllRemoteKey()
}