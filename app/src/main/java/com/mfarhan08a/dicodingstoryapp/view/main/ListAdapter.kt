package com.mfarhan08a.dicodingstoryapp.view.main

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mfarhan08a.dicodingstoryapp.data.model.Story
import com.mfarhan08a.dicodingstoryapp.databinding.ItemStoryBinding

class ListAdapter :
    PagingDataAdapter<Story, ListAdapter.ListViewHolder>(DIFF_CALLBACK) {

    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(story: Story, optionsCompat: ActivityOptionsCompat)
    }

    fun setOnItemClickCallBack(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ListViewHolder(binding: ItemStoryBinding) : RecyclerView.ViewHolder(binding.root) {
        var ivPhoto= binding.ivItemPhoto
        var tvName = binding.tvItemName
        var tvDescription = binding.tvItemDescription

        fun bind(story: Story?) {
            Glide.with(itemView.context)
                .load(story?.photoUrl)
                .into(ivPhoto)
            tvName.text = story?.name
            tvDescription.text = story?.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            ItemStoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val story = getItem(position)
        holder.bind(story)
        holder.itemView.setOnClickListener {
            val optionsCompat: ActivityOptionsCompat =
                ActivityOptionsCompat.makeSceneTransitionAnimation(
                    holder.itemView.context as Activity,
                    Pair(holder.ivPhoto, "photo"),
                    Pair(holder.tvName, "name"),
                    Pair(holder.tvDescription, "description")
                )
            onItemClickCallback.onItemClicked(story!!, optionsCompat)
        }

    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Story>() {
            override fun areItemsTheSame(oldItem: Story, newItem: Story): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Story, newItem: Story): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

}