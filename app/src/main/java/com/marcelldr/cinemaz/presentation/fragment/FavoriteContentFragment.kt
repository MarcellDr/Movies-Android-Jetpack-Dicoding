package com.marcelldr.cinemaz.presentation.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.marcelldr.cinemaz.data.repository.sources.local.room.entity.FavoriteEntity
import com.marcelldr.cinemaz.data.repository.sources.local.room.entity.MoviesEntity
import com.marcelldr.cinemaz.databinding.FragmentFavoriteContentBinding
import com.marcelldr.cinemaz.presentation.activity.DetailActivity
import com.marcelldr.cinemaz.presentation.adapter.FavoriteRVAdapter
import com.marcelldr.cinemaz.presentation.viewmodel.FavoriteViewModel
import com.marcelldr.cinemaz.presentation.viewmodel.ViewModelFactory
import com.marcelldr.cinemaz.utils.Injection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ingFavoriteContentFragment : Fragment() {
    private var category: String? = null
    private lateinit var binding: FragmentFavoriteContentBinding
    private lateinit var favoriteRVAdapter: FavoriteRVAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteContentBinding.inflate(inflater, container, false)
        favoriteRVAdapter = FavoriteRVAdapter()
        setUp()
        return binding.root
    }

    fun setCategory(category: String) {
        this.category = category
    }

    private fun setUp() {
        fun factory() {
            val factory = ViewModelFactory.getInstance(requireContext())
            val favoriteViewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]
            if (category == "MOVIES") {
                favoriteViewModel.setMovies()
                favoriteViewModel.getMovies().observe(viewLifecycleOwner, {
                    if (it != null) {
                        if (it.size == 0) {
                            binding.noFavorite.visibility = View.VISIBLE
                        } else {
                            binding.noFavorite.visibility = View.GONE
                        }
                        favoriteRVAdapter.submitList(it)
                    }
                })

            } else {
                favoriteViewModel.setTv()
                favoriteViewModel.getTv().observe(viewLifecycleOwner, {
                    if (it != null) {
                        if (it.size == 0) {
                            binding.noFavorite.visibility = View.VISIBLE
                        } else {
                            binding.noFavorite.visibility = View.GONE
                        }
                        favoriteRVAdapter.submitList(it)
                    }
                })
            }
        }

        factory()
        buildRecyclerView()
    }

    private fun buildRecyclerView() {
        binding.favoriteRV.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = favoriteRVAdapter
            favoriteRVAdapter.setOnItemClickCallback(object :
                FavoriteRVAdapter.OnItemClickCallback {
                override fun onItemClicked(favoriteEntity: FavoriteEntity) {
                    val data = MoviesEntity(
                        favoriteEntity.id,
                        favoriteEntity.title,
                        favoriteEntity.genre,
                        favoriteEntity.rating,
                        favoriteEntity.overview,
                        favoriteEntity.poster,
                        favoriteEntity.background
                    )
                    val intent = Intent(requireContext(), DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA, data)
                    intent.putExtra(DetailActivity.CATEGORY, category)
                    startActivity(intent)
                }

            })
            lifecycleScope.launch {
                favoriteRVAdapter.setDeleteFavoriteCallback(object :
                    FavoriteRVAdapter.DeleteFavoriteCallback {
                    override fun onItemClicked(favoriteEntity: FavoriteEntity) {
                        lifecycleScope.launch {
                            val db = Injection.provideDataRepository(requireContext())
                            withContext(Dispatchers.IO) {
                                db.deleteFavorite(favoriteEntity)
                            }
                        }
                    }
                })
            }

        }
    }
}