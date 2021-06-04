package com.marcelldr.cinemaz.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.marcelldr.cinemaz.R
import com.marcelldr.cinemaz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUp()
    }

    private fun setUp() {
        fun statusAndActionBar() {
            window.statusBarColor = resources.getColor(R.color.translucent, this.theme)
            supportActionBar?.hide()
        }
        fun binding() {
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)
        }
        fun bottomNavigation() {
            val hostFragment = findNavController(R.id.mainHostFragment)
            binding.mainBottomNav.setupWithNavController(hostFragment)
        }

        statusAndActionBar()
        binding()
        bottomNavigation()
    }
}