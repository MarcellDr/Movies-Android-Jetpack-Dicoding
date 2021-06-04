package com.marcelldr.cinemaz.data.repository.sources.local.room.dao

import androidx.paging.DataSource
import androidx.room.*
import com.marcelldr.cinemaz.data.repository.sources.local.room.entity.FavoriteEntity

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorite_entity WHERE category = :category")
    fun getFavorite(category: String): DataSource.Factory<Int, FavoriteEntity>

    @Query("SELECT * FROM favorite_entity WHERE id = :id LIMIT 1")
    fun getFavoriteById(id: Int): FavoriteEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(favoriteEntity: FavoriteEntity)

    @Delete
    fun deleteFavorite(favoriteEntity: FavoriteEntity?)
}