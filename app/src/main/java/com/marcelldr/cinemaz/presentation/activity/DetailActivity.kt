package com.marcelldr.cinemaz.presentation.activity

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.marcelldr.cinemaz.data.repository.sources.local.room.entity.FavoriteEntity
import com.marcelldr.cinemaz.data.repository.sources.local.room.entity.MoviesEntity
import com.marcelldr.cinemaz.databinding.ActivityDetailBinding
import com.marcelldr.cinemaz.utils.Injection
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private var favoriteEntity: FavoriteEntity? = null
    private var favorite: Boolean = false

    companion object {
        const val EXTRA = "extra"
        const val CATEGORY = "category"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUp()
    }

    private fun setUp() {
        val category = intent.getStringExtra(CATEGORY)
        val extra = intent.getParcelableExtra<MoviesEntity>(EXTRA) as MoviesEntity

        fun statusAndActionBar() {
            window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            window?.statusBarColor = Color.TRANSPARENT
            supportActionBar?.hide()
        }

        fun favoriteBehavior() {
            val db = Injection.provideDataRepository(applicationContext)
            lifecycleScope.launch {
                favoriteEntity = async(Dispatchers.IO) {
                    return@async db.getFavoriteById(extra.id)
                }.await()
                if (favoriteEntity != null) {
                    favorite = true
                    binding.detailFavorite.progress = 1F
                }
                binding.detailFavorite.setOnClickListener {
                    if (favorite) {
                        lifecycleScope.launch {
                            binding.detailFavorite.progress = 0F
                            binding.detailFavorite.pauseAnimation()
                            withContext(Dispatchers.IO) {
                                db.deleteFavorite(favoriteEntity!!)
                            }
                        }
                    } else {
                        lifecycleScope.launch {
                            favoriteEntity = FavoriteEntity(
                                extra.id,
                                category!!,
                                extra.title,
                                extra.genre,
                                extra.rating,
                                extra.overview,
                                extra.poster,
                                extra.background
                            )
                            binding.detailFavorite.playAnimation()
                            withContext(Dispatchers.IO) {
                                db.insertFavorite(favoriteEntity!!)
                            }
                        }
                    }
                    favorite = !favorite
                }
            }
        }

        fun binding() {
            binding = ActivityDetailBinding.inflate(layoutInflater)
            binding.detailTitle.text = extra.title
            binding.detailGenre.text = extra.genre
            binding.detailOverview.text = extra.overview
            binding.detailRating.text = extra.rating.toString()
            binding.detailRatingBar.rating = extra.rating.toFloat() / 2
            Glide.with(binding.detailBackground.context)
                .load(extra.background)
                .transform(BlurTransformation(25))
                .into(binding.detailBackground)
            Glide.with(binding.detailPoster.context).load(extra.poster).into(binding.detailPoster)

            binding.detailGoBack.setOnClickListener { finish() }

            favoriteBehavior()
            setContentView(binding.root)
        }

        statusAndActionBar()
        binding()
    }
}