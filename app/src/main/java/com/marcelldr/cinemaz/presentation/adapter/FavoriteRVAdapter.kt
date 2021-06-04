package com.marcelldr.cinemaz.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.marcelldr.cinemaz.data.repository.sources.local.room.entity.FavoriteEntity
import com.marcelldr.cinemaz.databinding.ListItemBinding

class FavoriteRVAdapter :
    PagedListAdapter<FavoriteEntity, FavoriteRVAdapter.ListViewHolder>(DIFF_CALLBACK) {
    private lateinit var onItemClickCallback: OnItemClickCallback
    private lateinit var deleteFavoriteCallback: DeleteFavoriteCallback

    inner class ListViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(favoriteEntity: FavoriteEntity) {
            with(binding) {
                itemTitle.text = favoriteEntity.title
                itemGenre.text = favoriteEntity.genre
                itemRating.text = favoriteEntity.rating.toString()
                itemOverview.text = favoriteEntity.overview
                Glide.with(itemPoster.context).load(favoriteEntity.poster).into(itemPoster)

                favoriteButton.progress = 1F
                favoriteButton.setOnClickListener {
                    binding.favoriteButton.progress = 0F
                    binding.favoriteButton.pauseAnimation()
                    deleteFavoriteCallback.onItemClicked(favoriteEntity)
                }
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<FavoriteEntity> =
            object : DiffUtil.ItemCallback<FavoriteEntity>() {
                override fun areItemsTheSame(
                    oldItem: FavoriteEntity,
                    newItem: FavoriteEntity
                ): Boolean {
                    return oldItem.title == newItem.title && oldItem.genre == newItem.genre && oldItem.poster == newItem.poster && oldItem.overview == newItem.overview && oldItem.background == newItem.background && oldItem.rating == newItem.rating
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(
                    oldItem: FavoriteEntity,
                    newItem: FavoriteEntity
                ): Boolean {
                    return oldItem == newItem
                }

            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(getItem(position) as FavoriteEntity)
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(getItem(holder.adapterPosition) as FavoriteEntity) }
    }

    interface OnItemClickCallback {
        fun onItemClicked(favoriteEntity: FavoriteEntity)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface DeleteFavoriteCallback {
        fun onItemClicked(favoriteEntity: FavoriteEntity)
    }

    fun setDeleteFavoriteCallback(deleteFavoriteCallback: DeleteFavoriteCallback) {
        this.deleteFavoriteCallback = deleteFavoriteCallback
    }
}