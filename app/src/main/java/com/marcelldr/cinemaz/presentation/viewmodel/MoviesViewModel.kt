package com.marcelldr.cinemaz.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.marcelldr.cinemaz.data.repository.DataRepository
import com.marcelldr.cinemaz.data.repository.sources.local.room.entity.MoviesEntity
import com.marcelldr.cinemaz.vo.Resource

class MoviesViewModel(private val dataRepository: DataRepository) : ViewModel() {
    private lateinit var movies: LiveData<Resource<PagedList<MoviesEntity>>>

    fun setMovies() {
        movies = dataRepository.getMovies()
    }

    fun getMovies() = movies

}