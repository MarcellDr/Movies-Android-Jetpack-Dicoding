package com.marcelldr.cinemaz.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.marcelldr.cinemaz.data.repository.DataRepository
import com.marcelldr.cinemaz.data.repository.sources.local.room.entity.MoviesEntity
import com.marcelldr.cinemaz.vo.Resource
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MoviesViewModelTest : TestCase() {
    private lateinit var moviesViewModel: MoviesViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataRepository: DataRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<MoviesEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<MoviesEntity>

    @Before
    public override fun setUp() {
        super.setUp()
        moviesViewModel = MoviesViewModel(dataRepository)
    }

    @Test
    fun testGetMovies() {
        val dummy = Resource.success(pagedList)
        Mockito.`when`(dummy.data?.size).thenReturn(5)
        val liveData = MutableLiveData<Resource<PagedList<MoviesEntity>>>()
        liveData.value = dummy

        Mockito.`when`(dataRepository.getMovies()).thenReturn(liveData)
        moviesViewModel.setMovies()
        val movies = moviesViewModel.getMovies().value?.data
        Mockito.verify(dataRepository).getMovies()
        assertNotNull(movies)
        assertEquals(5, movies?.size)

        moviesViewModel.getMovies().observeForever(observer)
        Mockito.verify(observer).onChanged(dummy)
    }
}