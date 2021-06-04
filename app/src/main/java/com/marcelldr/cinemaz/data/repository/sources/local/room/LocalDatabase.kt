package com.marcelldr.cinemaz.data.repository.sources.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.marcelldr.cinemaz.data.repository.sources.local.room.dao.FavoriteDao
import com.marcelldr.cinemaz.data.repository.sources.local.room.dao.MoviesDao
import com.marcelldr.cinemaz.data.repository.sources.local.room.dao.TvDao
import com.marcelldr.cinemaz.data.repository.sources.local.room.entity.FavoriteEntity
import com.marcelldr.cinemaz.data.repository.sources.local.room.entity.MoviesEntity
import com.marcelldr.cinemaz.data.repository.sources.local.room.entity.TvEntity

@Database(
    entities = [MoviesEntity::class, TvEntity::class, FavoriteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
    abstract fun tvDao(): TvDao
    abstract fun favoriteDao(): FavoriteDao

    companion object {
        @Volatile
        private var INSTANCE: LocalDatabase? = null

        fun getInstance(context: Context): LocalDatabase = INSTANCE ?: synchronized(this) {
            Room.databaseBuilder(
                context.applicationContext,
                LocalDatabase::class.java,
                "LocalDatabase.db"
            ).build().apply {
                INSTANCE = this
            }
        }
    }
}
