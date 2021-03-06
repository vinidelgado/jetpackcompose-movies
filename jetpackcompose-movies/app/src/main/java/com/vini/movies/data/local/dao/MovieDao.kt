package com.vini.movies.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vini.movies.domain.model.Movie

@Dao
interface MovieDao {
    @Query("Select * FROM movie_table")
    fun getAllMovies():PagingSource<Int,Movie>

    @Query("Select * FROM movie_table WHERE id=:movieId")
    fun getSelectedMovie(movieId:Int):Movie

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovie(movies:List<Movie>)

    @Query("DELETE FROM movie_table")
    suspend fun deleteAllMovies()
}