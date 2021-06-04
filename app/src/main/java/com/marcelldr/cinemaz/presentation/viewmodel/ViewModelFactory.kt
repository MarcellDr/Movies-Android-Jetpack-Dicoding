package com.marcelldr.cinemaz.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.marcelldr.cinemaz.data.repository.DataRepository
import com.marcelldr.cinemaz.utils.Injection

class ViewModelFactory private constructor(private val dataRepository: DataRepository) :
    ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory = INSTANCE ?: synchronized(this) {
            INSTANCE ?: ViewModelFactory(Injection.provideDataRepository(context)).apply {
                INSTANCE = this
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MoviesViewModel::class.java) -> {
                val viewModel = MoviesViewModel(dataRepository)
                viewModel.setMovies()
                viewModel as T
            }
            modelClass.isAssignableFrom(TvViewModel::class.java) -> {
                val viewModel = TvViewModel(dataRepository)
                viewModel.setTv()
                viewModel as T
            }
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(dataRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}