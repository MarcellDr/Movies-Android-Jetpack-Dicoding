package com.marcelldr.cinemaz.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.marcelldr.cinemaz.data.repository.DataRepository
import com.marcelldr.cinemaz.data.repository.sources.local.room.entity.TvEntity
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
class TvViewModelTest : TestCase() {
    private lateinit var tvViewModel: TvViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataRepository: DataRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TvEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<TvEntity>

    @Before
    public override fun setUp() {
        super.setUp()
        tvViewModel = TvViewModel(dataRepository)
    }

    @Test
    fun testGetTv() {
        val dummy = Resource.success(pagedList)
        Mockito.`when`(dummy.data?.size).thenReturn(5)
        val liveData = MutableLiveData<Resource<PagedList<TvEntity>>>()
        liveData.value = dummy

        Mockito.`when`(dataRepository.getTv()).thenReturn(liveData)
        tvViewModel.setTv()
        val tv = tvViewModel.getTv().value?.data
        Mockito.verify(dataRepository).getTv()
        assertNotNull(tv)
        assertEquals(5, tv?.size)

        tvViewModel.getTv().observeForever(observer)
        Mockito.verify(observer).onChanged(dummy)
    }
}