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
import com.marcelldr.cinemaz.data.repository.sources.local.room.entity.TvEntity
import com.marcelldr.cinemaz.databinding.FragmentTvBinding
import com.marcelldr.cinemaz.presentation.activity.DetailActivity
import com.marcelldr.cinemaz.presentation.adapter.TvRVAdapter
import com.marcelldr.cinemaz.presentation.viewmodel.TvViewModel
import com.marcelldr.cinemaz.presentation.viewmodel.ViewModelFactory
import com.marcelldr.cinemaz.vo.Status

class TVFragment : Fragment() {
    private lateinit var binding: FragmentTvBinding
    private var tvRVAdapter = TvRVAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvBinding.inflate(inflater, container, false)
        setUp()
        return binding.root
    }

    private fun setUp() {
        fun factory() {
            val factory = ViewModelFactory.getInstance(requireContext())
            val tvViewModel = ViewModelProvider(this, factory)[TvViewModel::class.java]
            tvViewModel.getTv().observe(viewLifecycleOwner, {
                if (it != null) {
                    when (it.status) {
                        Status.LOADING -> binding.tvLoading.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            binding.tvLoading.visibility = View.GONE
                            tvRVAdapter.submitList(it.data)
                        }
                        Status.ERROR -> {
                            binding.tvLoading.visibility = View.GONE
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
        binding.tvRV.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = tvRVAdapter
            tvRVAdapter.setOnItemClickCallback(object : TvRVAdapter.OnItemClickCallback {
                override fun onItemClicked(tvEntity: TvEntity) {
                    val intent = Intent(requireContext(), DetailActivity::class.java)
                    val cast = MoviesEntity(
                        tvEntity.id,
                        tvEntity.title,
                        tvEntity.genre,
                        tvEntity.rating,
                        tvEntity.overview,
                        tvEntity.poster,
                        tvEntity.background
                    )
                    intent.putExtra(DetailActivity.EXTRA, cast)
                    intent.putExtra(DetailActivity.CATEGORY, "TV")
                    startActivity(intent)
                }
            })
        }
    }
}