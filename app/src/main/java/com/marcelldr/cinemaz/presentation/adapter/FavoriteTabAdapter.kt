package com.marcelldr.cinemaz.presentation.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.marcelldr.cinemaz.presentation.fragment.FavoriteContentFragment

class FavoriteTabAdapter(activity: AppCompatActivity): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {

        val fragment: Fragment? = when(position) {
            0 -> {
                val temp = FavoriteContentFragment()
                temp.setCategory("MOVIES")
                temp
            }
            1 -> {
                val temp = FavoriteContentFragment()
                temp.setCategory("TV")
                temp
            }
            else -> null
        }
        return fragment as Fragment
    }
}