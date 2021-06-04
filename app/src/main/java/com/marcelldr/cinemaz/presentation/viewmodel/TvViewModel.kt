package com.marcelldr.cinemaz.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.marcelldr.cinemaz.data.repository.DataRepository
import com.marcelldr.cinemaz.data.repository.sources.local.room.entity.TvEntity
import com.marcelldr.cinemaz.vo.Resource

class TvViewModel(private val dataRepository: DataRepository) : ViewModel() {
    private lateinit var tv: LiveData<Resource<PagedList<TvEntity>>>

    fun setTv() {
        tv = dataRepository.getTv()
    }

    fun getTv() = tv

}