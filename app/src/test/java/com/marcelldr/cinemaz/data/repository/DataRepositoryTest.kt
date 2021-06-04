package com.marcelldr.cinemaz.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DataSource
import com.marcelldr.cinemaz.data.repository.sources.local.LocalDataSource
import com.marcelldr.cinemaz.data.repository.sources.local.room.entity.FavoriteEntity
import com.marcelldr.cinemaz.data.repository.sources.local.room.entity.MoviesEntity
import com.marcelldr.cinemaz.data.repository.sources.local.room.entity.TvEntity
import com.marcelldr.cinemaz.data.repository.sources.remote.RemoteDataSource
import com.marcelldr.cinemaz.data.dummy.DataDummy
import com.marcelldr.cinemaz.utils.AppExecutors
import com.marcelldr.cinemaz.utils.PagedListUtil
import com.marcelldr.cinemaz.vo.Resource
import com.nhaarman.mockitokotlin2.doNothing
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class DataRepositoryTest : TestCase() {
    private lateinit var dataRepository: FakeDataRepository

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var localDataSource: LocalDataSource

    @Mock
    private lateinit var remoteDataSource: RemoteDataSource

    @Mock
    private lateinit var appExecutors: AppExecutors

    @Before
    public override fun setUp() {
        super.setUp()
        dataRepository = FakeDataRepository(localDataSource, remoteDataSource, appExecutors)
    }

    @Test
    fun testGetMovies() {
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MoviesEntity>
        Mockito.`when`(localDataSource.getMovies()).thenReturn(dataSourceFactory)
        dataRepository.getMovies()

        val dummy = DataDummy.getMovies()
        val moviesEntity = Resource.success(PagedListUtil.mockPagedList(dummy))
        Mockito.verify(localDataSource).getMovies()
        assertNotNull(moviesEntity.data)
        assertEquals(dummy.size.toLong(), moviesEntity.data?.size?.toLong())
        assertEquals(dummy[0].id, (moviesEntity.data?.get(0) as MoviesEntity).id)
        assertEquals(dummy[0].title, (moviesEntity.data?.get(0) as MoviesEntity).title)
        assertEquals(dummy[0].genre, (moviesEntity.data?.get(0) as MoviesEntity).genre)
        assertEquals(dummy[0].rating, (moviesEntity.data?.get(0) as MoviesEntity).rating)
        assertEquals(dummy[0].overview, (moviesEntity.data?.get(0) as MoviesEntity).overview)
        assertEquals(dummy[0].poster, (moviesEntity.data?.get(0) as MoviesEntity).poster)
        assertEquals(dummy[0].background, (moviesEntity.data?.get(0) as MoviesEntity).background)
    }

    @Test
    fun testGetTv() {
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvEntity>
        Mockito.`when`(localDataSource.getTv()).thenReturn(dataSourceFactory)
        dataRepository.getTv()

        val dummy = DataDummy.getTv()
        val tvEntity = Resource.success(PagedListUtil.mockPagedList(dummy))
        Mockito.verify(localDataSource).getTv()
        assertNotNull(tvEntity.data)
        assertEquals(dummy.size.toLong(), tvEntity.data?.size?.toLong())
        assertEquals(dummy[0].id, (tvEntity.data?.get(0) as TvEntity).id)
        assertEquals(dummy[0].title, (tvEntity.data?.get(0) as TvEntity).title)
        assertEquals(dummy[0].genre, (tvEntity.data?.get(0) as TvEntity).genre)
        assertEquals(dummy[0].rating, (tvEntity.data?.get(0) as TvEntity).rating)
        assertEquals(dummy[0].overview, (tvEntity.data?.get(0) as TvEntity).overview)
        assertEquals(dummy[0].poster, (tvEntity.data?.get(0) as TvEntity).poster)
        assertEquals(dummy[0].background, (tvEntity.data?.get(0) as TvEntity).background)
    }

    @Test
    fun testGetFavorite() {
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, FavoriteEntity>
        Mockito.`when`(localDataSource.getFavorite("MOVIES")).thenReturn(dataSourceFactory)
        dataRepository.getFavorite("MOVIES")

        val dummy = DataDummy.getMoviesFavorite()
        val favoriteEntity = Resource.success(PagedListUtil.mockPagedList(dummy))
        Mockito.verify(localDataSource).getFavorite("MOVIES")
        assertNotNull(favoriteEntity.data)
        assertEquals(dummy.size.toLong(), favoriteEntity.data?.size?.toLong())
        assertEquals(dummy[0].id, (favoriteEntity.data?.get(0) as FavoriteEntity).id)
        assertEquals(dummy[0].category, (favoriteEntity.data?.get(0) as FavoriteEntity).category)
        assertEquals(dummy[0].title, (favoriteEntity.data?.get(0) as FavoriteEntity).title)
        assertEquals(dummy[0].genre, (favoriteEntity.data?.get(0) as FavoriteEntity).genre)
        assertEquals(dummy[0].rating, (favoriteEntity.data?.get(0) as FavoriteEntity).rating)
        assertEquals(dummy[0].overview, (favoriteEntity.data?.get(0) as FavoriteEntity).overview)
        assertEquals(dummy[0].poster, (favoriteEntity.data?.get(0) as FavoriteEntity).poster)
        assertEquals(dummy[0].background, (favoriteEntity.data?.get(0) as FavoriteEntity).background)
    }

    @Test
    fun testGetFavoriteById() {
        val dummy = DataDummy.getMoviesFavorite()
        Mockito.`when`(localDataSource.getFavoriteById(dummy[0].id)).thenReturn(dummy[0])
        val favoriteEntity = dataRepository.getFavoriteById(dummy[0].id)
        Mockito.verify(localDataSource).getFavoriteById(dummy[0].id)

        assertNotNull(favoriteEntity)
        assertEquals(dummy[0].id, favoriteEntity.id)
        assertEquals(dummy[0].category, favoriteEntity.category)
        assertEquals(dummy[0].title, favoriteEntity.title)
        assertEquals(dummy[0].genre, favoriteEntity.genre)
        assertEquals(dummy[0].rating, favoriteEntity.rating)
        assertEquals(dummy[0].overview, favoriteEntity.overview)
        assertEquals(dummy[0].poster, favoriteEntity.poster)
        assertEquals(dummy[0].background, favoriteEntity.background)
    }

    @Test
    fun testInsertFavorite() {
        val dummy = DataDummy.getMoviesFavorite()[0]
        doNothing().`when`(localDataSource).insertFavorite(dummy)
        dataRepository.insertFavorite(dummy)
        Mockito.verify(localDataSource).insertFavorite(dummy)

        Mockito.`when`(localDataSource.getFavoriteById(dummy.id)).thenReturn(dummy)
        val favoriteEntity = dataRepository.getFavoriteById(dummy.id)
        Mockito.verify(localDataSource).getFavoriteById(dummy.id)

        assertNotNull(favoriteEntity)
        assertEquals(dummy.id, favoriteEntity.id)
        assertEquals(dummy.category, favoriteEntity.category)
        assertEquals(dummy.title, favoriteEntity.title)
        assertEquals(dummy.genre, favoriteEntity.genre)
        assertEquals(dummy.rating, favoriteEntity.rating)
        assertEquals(dummy.overview, favoriteEntity.overview)
        assertEquals(dummy.poster, favoriteEntity.poster)
        assertEquals(dummy.background, favoriteEntity.background)
    }

    @Test
    fun testDeleteFavorite() {
        val dummy = DataDummy.getMoviesFavorite()[0]
        Mockito.`when`(localDataSource.getFavoriteById(dummy.id)).thenReturn(dummy)
        val favoriteEntity = dataRepository.getFavoriteById(dummy.id)
        Mockito.verify(localDataSource).getFavoriteById(dummy.id)

        doNothing().`when`(localDataSource).deleteFavorite(favoriteEntity)
        dataRepository.deleteFavorite(favoriteEntity)
        Mockito.verify(localDataSource).deleteFavorite(favoriteEntity)
    }
}