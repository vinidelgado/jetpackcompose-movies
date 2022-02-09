package com.vini.movies.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vini.movies.domain.model.Genre

@Dao
interface GenreDao {
    @Query("Select * FROM genre_table")
    fun getAllGenres():List<Genre>

    @Query("Select * FROM genre_table WHERE id=:genreId")
    fun getSelectedGenre(genreId:Int):Genre

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGenre(genres:List<Genre>)

    @Query("DELETE FROM genre_table")
    suspend fun deleteAllGenres()
}