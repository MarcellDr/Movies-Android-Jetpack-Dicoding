package com.marcelldr.cinemaz.data.repository.sources.remote

import com.marcelldr.cinemaz.data.repository.sources.remote.api.MovieApi
import com.marcelldr.cinemaz.data.repository.sources.remote.response.DataResponse
import com.marcelldr.cinemaz.data.repository.sources.remote.utils.ApiResponse

class RemoteDataSource private constructor(private val movieApi: MovieApi) {
    companion object {
        @Volatile
        private var INSTANCE: RemoteDataSource? = null
        fun getInstance(movieApi: MovieApi): RemoteDataSource = INSTANCE ?: synchronized(this) {
            INSTANCE ?: RemoteDataSource(movieApi).apply { INSTANCE = this }
        }
    }

    interface RemoteDataCallback {
        fun onResponse(response: ApiResponse<List<DataResponse>>)
    }

    fun getMovies(remoteDataCallback: RemoteDataCallback) {
        movieApi.getMovies(object : MovieApi.MovieAPICallback {
            override fun onResponse(response: List<DataResponse>) {
                remoteDataCallback.onResponse(ApiResponse.success(response))
            }
        })
    }

    fun getTv(remoteDataCallback: RemoteDataCallback) {
        movieApi.getTv(object : MovieApi.MovieAPICallback {
            override fun onResponse(response: List<DataResponse>) {
                remoteDataCallback.onResponse(ApiResponse.success(response))
            }
        })
    }
}