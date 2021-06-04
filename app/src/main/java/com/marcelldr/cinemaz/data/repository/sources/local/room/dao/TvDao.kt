package com.marcelldr.cinemaz.data.repository.sources.local.room.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.marcelldr.cinemaz.data.repository.sources.local.room.entity.TvEntity

@Dao
interface TvDao {
    @Query("SELECT * FROM tv_entity")
    fun getTv(): DataSource.Factory<Int, TvEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setTv(movies: List<TvEntity>)
}