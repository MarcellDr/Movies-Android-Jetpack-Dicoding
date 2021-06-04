package com.marcelldr.cinemaz.data.repository.sources.local

import androidx.paging.DataSource
import com.marcelldr.cinemaz.data.repository.sources.local.room.dao.FavoriteDao
import com.marcelldr.cinemaz.data.repository.sources.local.room.dao.MoviesDao
import com.marcelldr.cinemaz.data.repository.sources.local.room.dao.TvDao
import com.marcelldr.cinemaz.data.repository.sources.local.room.entity.FavoriteEntity
import com.marcelldr.cinemaz.data.repository.sources.local.room.entity.MoviesEntity
import com.marcelldr.cinemaz.data.repository.sources.local.room.entity.TvEntity

class LocalDataSource private constructor(
    private val moviesDao: MoviesDao,
    private val tvDao: TvDao,
    private val favoriteDao: FavoriteDao
) {
    companion object {
        private val INSTANCE: LocalDataSource? = null
        fun getInstance(
            moviesDao: MoviesDao,
            tvDao: TvDao,
            favoriteDao: FavoriteDao
        ): LocalDataSource = INSTANCE ?: LocalDataSource(moviesDao, tvDao, favoriteDao)
    }


    fun getMovies(): DataSource.Factory<Int, MoviesEntity> = moviesDao.getMovies()

    fun getTv(): DataSource.Factory<Int, TvEntity> = tvDao.getTv()

    fun getFavorite(category: String): DataSource.Factory<Int, FavoriteEntity> =
        favoriteDao.getFavorite(category)

    fun setMovies(movies: List<MoviesEntity>) = moviesDao.setMovies(movies)

    fun setTv(tv: List<TvEntity>) = tvDao.setTv(tv)

    fun getFavoriteById(id: Int): FavoriteEntity = favoriteDao.getFavoriteById(id)

    fun insertFavorite(favoriteEntity: FavoriteEntity) = favoriteDao.insertFavorite(favoriteEntity)

    fun deleteFavorite(favoriteEntity: FavoriteEntity) = favoriteDao.deleteFavorite(favoriteEntity)
}