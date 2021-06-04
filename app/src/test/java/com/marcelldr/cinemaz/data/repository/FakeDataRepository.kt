package com.marcelldr.cinemaz.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.marcelldr.cinemaz.data.repository.sources.local.LocalDataSource
import com.marcelldr.cinemaz.data.repository.sources.local.room.entity.FavoriteEntity
import com.marcelldr.cinemaz.data.repository.sources.local.room.entity.MoviesEntity
import com.marcelldr.cinemaz.data.repository.sources.local.room.entity.TvEntity
import com.marcelldr.cinemaz.data.repository.sources.remote.RemoteDataSource
import com.marcelldr.cinemaz.data.repository.sources.remote.response.DataResponse
import com.marcelldr.cinemaz.data.repository.sources.remote.utils.ApiResponse
import com.marcelldr.cinemaz.utils.AppExecutors
import com.marcelldr.cinemaz.vo.Resource
import com.marcelldr.cinemaz.utils.IdlingResource

class FakeDataRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val appExecutors: AppExecutors
) {
    fun getMovies(): LiveData<Resource<PagedList<MoviesEntity>>> {
        return object :
            NetworkBoundResource<PagedList<MoviesEntity>, List<DataResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<MoviesEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MoviesEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            public override fun createCall(): LiveData<ApiResponse<List<DataResponse>>> {
                IdlingResource.increment()
                val liveData = MutableLiveData<ApiResponse<List<DataResponse>>>()
                remoteDataSource.getMovies(object : RemoteDataSource.RemoteDataCallback {
                    override fun onResponse(response: ApiResponse<List<DataResponse>>) {
                        liveData.postValue(response)
                        IdlingResource.decrement()
                    }
                })
                return liveData
            }

            public override fun saveCallResult(data: List<DataResponse>) {
                val list = mutableListOf<MoviesEntity>()
                for (item in data) {
                    val movies = MoviesEntity(
                        item.id,
                        item.title,
                        item.genre,
                        item.rating,
                        item.overview,
                        item.poster,
                        item.background
                    )
                    list.add(movies)
                }
                localDataSource.setMovies(list)
            }

        }.asLiveData()
    }

    fun getTv(): LiveData<Resource<PagedList<TvEntity>>> {
        return object :
            NetworkBoundResource<PagedList<TvEntity>, List<DataResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TvEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getTv(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<DataResponse>>> {
                IdlingResource.increment()
                val liveData = MutableLiveData<ApiResponse<List<DataResponse>>>()
                remoteDataSource.getTv(object : RemoteDataSource.RemoteDataCallback {
                    override fun onResponse(response: ApiResponse<List<DataResponse>>) {
                        liveData.postValue(response)
                        IdlingResource.decrement()
                    }
                })
                return liveData
            }

            override fun saveCallResult(data: List<DataResponse>) {
                val list = mutableListOf<TvEntity>()
                for (item in data) {
                    val tv = TvEntity(
                        item.id,
                        item.title,
                        item.genre,
                        item.rating,
                        item.overview,
                        item.poster,
                        item.background
                    )
                    list.add(tv)
                }
                localDataSource.setTv(list)
            }

        }.asLiveData()
    }

    fun getFavorite(category: String): LiveData<PagedList<FavoriteEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavorite(category), config).build()
    }

    fun getFavoriteById(id: Int): FavoriteEntity = localDataSource.getFavoriteById(id)

    fun insertFavorite(favoriteEntity: FavoriteEntity) =
        localDataSource.insertFavorite(favoriteEntity)

    fun deleteFavorite(favoriteEntity: FavoriteEntity) =
        localDataSource.deleteFavorite(favoriteEntity)
}