package com.vini.movies.domain.model

import androidx.room.TypeConverter

private const val SEPARATOR = ","

class GenresTypeConverters {

    @TypeConverter
    fun listGenreToString(listGenre: MutableList<Int>?):String? {
        return listGenre?.map { it }?.joinToString(separator = SEPARATOR)
    }

    @TypeConverter
    fun stringToGenreList(stringGenre: String?) =
        stringGenre?.let {
            stringGenre.split(SEPARATOR).forEach {
                it
            }
        }
}