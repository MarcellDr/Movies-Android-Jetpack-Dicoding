package com.marcelldr.cinemaz.utils

import android.content.Context
import com.marcelldr.cinemaz.data.repository.DataRepository
import com.marcelldr.cinemaz.data.repository.sources.local.LocalDataSource
import com.marcelldr.cinemaz.data.repository.sources.local.room.LocalDatabase
import com.marcelldr.cinemaz.data.repository.sources.remote.RemoteDataSource
import com.marcelldr.cinemaz.data.repository.sources.remote.api.MovieApi

object Injection {
    fun provideDataRepository(context: Context): DataRepository {
        val movieApi = MovieApi.getInstance(context)
        val database = LocalDatabase.getInstance(context)
        val localDataSource = LocalDataSource.getInstance(
            database.moviesDao(),
            database.tvDao(),
            database.favoriteDao()
        )
        val remoteDataSource = RemoteDataSource.getInstance(movieApi)
        val appExecutors = AppExecutors()
        return DataRepository.getInstance(localDataSource, remoteDataSource, appExecutors)
    }
}