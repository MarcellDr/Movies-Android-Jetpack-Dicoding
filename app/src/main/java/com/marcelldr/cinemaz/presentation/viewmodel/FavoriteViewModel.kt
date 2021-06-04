package com.marcelldr.cinemaz.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.marcelldr.cinemaz.data.repository.DataRepository
import com.marcelldr.cinemaz.data.repository.sources.local.room.entity.FavoriteEntity

class FavoriteViewModel(private val dataRepository: DataRepository) : ViewModel() {
    private lateinit var moviesFavorite: LiveData<PagedList<FavoriteEntity>>
    private lateinit var tvFavorite: LiveData<PagedList<FavoriteEntity>>


    fun setMovies() {
        moviesFavorite = dataRepository.getFavorite("MOVIES")
    }

    fun setTv() {
        tvFavorite = dataRepository.getFavorite("TV")
    }

    fun getMovies() = moviesFavorite

    fun getTv() = tvFavorite
}