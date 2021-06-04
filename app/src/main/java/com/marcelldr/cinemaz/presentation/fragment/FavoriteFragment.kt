package com.marcelldr.cinemaz.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.marcelldr.cinemaz.R
import com.marcelldr.cinemaz.databinding.FragmentFavoriteBinding
import com.marcelldr.cinemaz.presentation.adapter.FavoriteTabAdapter

class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        setUp()
        return binding.root
    }

    private fun setUp() {
        fun tabManager() {
            val tabs: ArrayList<String> = ArrayList()
            tabs.add(resources.getString(R.string.movies))
            tabs.add(resources.getString(R.string.tv_series))
            val tabAdapter = FavoriteTabAdapter(requireActivity() as AppCompatActivity)
            binding.favoritePager.adapter = tabAdapter
            TabLayoutMediator(
                binding.favoriteTabManager,
                binding.favoritePager
            ) { tab, position -> tab.text = tabs[position] }.attach()
        }
        tabManager()
    }
}