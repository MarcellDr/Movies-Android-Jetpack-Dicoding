package com.marcelldr.cinemaz.presentation.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.marcelldr.cinemaz.data.repository.sources.local.room.entity.MoviesEntity
import com.marcelldr.cinemaz.databinding.FragmentMoviesBinding
import com.marcelldr.cinemaz.presentation.activity.DetailActivity
import com.marcelldr.cinemaz.presentation.adapter.MoviesRVAdapter
import com.marcelldr.cinemaz.presentation.viewmodel.MoviesViewModel
import com.marcelldr.cinemaz.presentation.viewmodel.ViewModelFactory
import com.marcelldr.cinemaz.vo.Status

class MoviesFragment : Fragment() {
    private lateinit var binding: FragmentMoviesBinding
    private var moviesRVAdapter = MoviesRVAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesBinding.inflate(inflater, container, false)
        setUp()
        return binding.root
    }

    private fun setUp() {
        fun factory() {
            val factory = ViewModelFactory.getInstance(requireContext())
            val moviesViewModel = ViewModelProvider(this, factory)[MoviesViewModel::class.java]
            moviesViewModel.getMovies().observe(viewLifecycleOwner, {
                if (it != null) {
                    when (it.status) {
                        Status.LOADING -> binding.moviesLoading.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            binding.moviesLoading.visibility = View.GONE
                            moviesRVAdapter.submitList(it.data)
                        }
                        Status.ERROR -> {
                            binding.moviesLoading.visibility = View.GONE
                            Toast.makeText(context, "Connection Error", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        }
        factory()
        buildRecyclerView()
    }

    private fun buildRecyclerView() {
        binding.moviesRV.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = moviesRVAdapter
            moviesRVAdapter.setOnItemClickCallback(object : MoviesRVAdapter.OnItemClickCallback {
                override fun onItemClicked(moviesEntity: MoviesEntity) {
                    val intent = Intent(requireContext(), DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA, moviesEntity)
                    intent.putExtra(DetailActivity.CATEGORY, "MOVIES")
                    startActivity(intent)
                }
            })
        }
    }
}