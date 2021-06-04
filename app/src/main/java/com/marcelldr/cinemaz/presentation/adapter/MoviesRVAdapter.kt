package com.marcelldr.cinemaz.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.marcelldr.cinemaz.data.repository.sources.local.room.entity.MoviesEntity
import com.marcelldr.cinemaz.databinding.ListItemBinding

class MoviesRVAdapter :
    PagedListAdapter<MoviesEntity, MoviesRVAdapter.ListViewHolder>(DIFF_CALLBACK) {
    private lateinit var onItemClickCallback: OnItemClickCallback

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<MoviesEntity> =
            object : DiffUtil.ItemCallback<MoviesEntity>() {
                override fun areItemsTheSame(
                    oldItem: MoviesEntity,
                    newItem: MoviesEntity
                ): Boolean {
                    return oldItem.title == newItem.title && oldItem.genre == newItem.genre && oldItem.poster == newItem.poster && oldItem.overview == newItem.overview && oldItem.background == newItem.background && oldItem.rating == newItem.rating
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(
                    oldItem: MoviesEntity,
                    newItem: MoviesEntity
                ): Boolean {
                    return oldItem == newItem
                }

            }
    }

    inner class ListViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(moviesEntity: MoviesEntity) {
            with(binding) {
                itemTitle.text = moviesEntity.title
                itemGenre.text = moviesEntity.genre
                itemRating.text = moviesEntity.rating.toString()
                itemOverview.text = moviesEntity.overview
                Glide.with(itemPoster.context).load(moviesEntity.poster).into(itemPoster)

                favoriteButton.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(getItem(position) as MoviesEntity)
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(getItem(holder.adapterPosition) as MoviesEntity) }
    }

    interface OnItemClickCallback {
        fun onItemClicked(moviesEntity: MoviesEntity)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
}