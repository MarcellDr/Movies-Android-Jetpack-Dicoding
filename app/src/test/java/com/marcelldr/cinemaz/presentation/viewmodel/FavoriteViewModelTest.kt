package com.marcelldr.cinemaz.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.marcelldr.cinemaz.data.repository.DataRepository
import com.marcelldr.cinemaz.data.repository.sources.local.room.entity.FavoriteEntity
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest : TestCase() {
    private lateinit var favoriteViewModel: FavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataRepository: DataRepository

    @Mock
    private lateinit var observer: Observer<PagedList<FavoriteEntity>>

    @Mock
    private lateinit var pagedList: PagedList<FavoriteEntity>

    @Before
    public override fun setUp() {
        super.setUp()
        favoriteViewModel = FavoriteViewModel(dataRepository)
    }

    @Test
    fun testGetMovies() {
        val dummy = pagedList
        Mockito.`when`(dummy.size).thenReturn(5)
        val liveData = MutableLiveData<PagedList<FavoriteEntity>>()
        liveData.value = dummy

        Mockito.`when`(dataRepository.getFavorite("MOVIES")).thenReturn(liveData)
        favoriteViewModel.setMovies()
        val movies = favoriteViewModel.getMovies().value
        Mockito.verify(dataRepository).getFavorite("MOVIES")
        assertNotNull(movies)
        assertEquals(5, movies?.size)

        favoriteViewModel.getMovies().observeForever(observer)
        Mockito.verify(observer).onChanged(dummy)
    }

    @Test
    fun testGetTv() {
        val dummy = pagedList
        Mockito.`when`(dummy.size).thenReturn(5)
        val liveData = MutableLiveData<PagedList<FavoriteEntity>>()
        liveData.value = dummy

        Mockito.`when`(dataRepository.getFavorite("TV")).thenReturn(liveData)
        favoriteViewModel.setTv()
        val tv = favoriteViewModel.getTv().value
        Mockito.verify(dataRepository).getFavorite("TV")
        assertNotNull(tv)
        assertEquals(5, tv?.size)

        favoriteViewModel.getTv().observeForever(observer)
        Mockito.verify(observer).onChanged(dummy)
    }
}