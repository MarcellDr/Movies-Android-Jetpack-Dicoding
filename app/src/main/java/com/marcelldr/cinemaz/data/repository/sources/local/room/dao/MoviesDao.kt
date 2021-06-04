package com.marcelldr.cinemaz.data.repository.sources.local.room.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.marcelldr.cinemaz.data.repository.sources.local.room.entity.MoviesEntity

@Dao
interface MoviesDao {
    @Query("SELECT * FROM movies_entity")
    fun getMovies(): DataSource.Factory<Int, MoviesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setMovies(movies: List<MoviesEntity>)
}