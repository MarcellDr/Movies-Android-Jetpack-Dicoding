package com.marcelldr.cinemaz.presentation.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.marcelldr.cinemaz.R
import com.marcelldr.cinemaz.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUp()
        launchIntent()
    }


    private fun setUp() {
        fun statusAndActionBar() {
            window.statusBarColor = resources.getColor(R.color.primary, this.theme)
            supportActionBar?.hide()
        }
        fun binding() {
            binding = ActivitySplashBinding.inflate(layoutInflater)
            setContentView(binding.root)
        }

        statusAndActionBar()
        binding()
    }

    private fun launchIntent() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }, 5000)
    }
}