package com.vini.movies.domain.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vini.movies.domain.model.Movie

@Dao
interface MovieDao {
    @Query("Select * FROM movie_table ORDER BY id ASC")
    fun getAllMovies():PagingSource<Int,Movie>

    @Query("Select * FROM movie_table WHERE id=:movieId")
    fun getSelectedMovie(movieId:Int):Movie

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovie(movies:List<Movie>)

    @Query("DELETE FROM movie_table")
    suspend fun deleteAllMovies()
}