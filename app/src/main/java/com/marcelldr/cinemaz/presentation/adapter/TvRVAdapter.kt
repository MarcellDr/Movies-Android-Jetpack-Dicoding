package com.marcelldr.cinemaz.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.marcelldr.cinemaz.data.repository.sources.local.room.entity.TvEntity
import com.marcelldr.cinemaz.databinding.ListItemBinding

class TvRVAdapter :
    PagedListAdapter<TvEntity, TvRVAdapter.ListViewHolder>(DIFF_CALLBACK) {
    private lateinit var onItemClickCallback: OnItemClickCallback

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<TvEntity> =
            object : DiffUtil.ItemCallback<TvEntity>() {
                override fun areItemsTheSame(
                    oldItem: TvEntity,
                    newItem: TvEntity
                ): Boolean {
                    return oldItem.title == newItem.title && oldItem.genre == newItem.genre && oldItem.poster == newItem.poster && oldItem.overview == newItem.overview && oldItem.background == newItem.background && oldItem.rating == newItem.rating
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(
                    oldItem: TvEntity,
                    newItem: TvEntity
                ): Boolean {
                    return oldItem == newItem
                }

            }
    }

    inner class ListViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvEntity: TvEntity) {
            with(binding) {
                itemTitle.text = tvEntity.title
                itemGenre.text = tvEntity.genre
                itemRating.text = tvEntity.rating.toString()
                itemOverview.text = tvEntity.overview
                Glide.with(itemPoster.context).load(tvEntity.poster).into(itemPoster)

                favoriteButton.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(getItem(position) as TvEntity)
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(getItem(holder.adapterPosition) as TvEntity) }
    }

    interface OnItemClickCallback {
        fun onItemClicked(tvEntity: TvEntity)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
}