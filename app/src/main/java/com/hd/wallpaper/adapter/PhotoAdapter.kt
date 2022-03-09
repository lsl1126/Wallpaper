package com.hd.wallpaper.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hd.wallpaper.bean.Photo
import com.hd.wallpaper.databinding.ListItemPhotoBinding
import com.hd.wallpaper.uitls.getWidthPixels

class PhotoAdapter(private val mContext: Context) :
    PagingDataAdapter<Photo, PhotoAdapter.MyViewHolder>(PhotoComparator) {

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val params = holder.imgPhoto.layoutParams
        params.width = (getWidthPixels(mContext) - 70) / 3
        val item = getItem(position)
        Glide.with(mContext)
            .load("${item?.src?.original}?auto=compress&cs=tinysrgb&fit=crop&h=1920&w=1080")
            .into(holder.imgPhoto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ListItemPhotoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class MyViewHolder(binding: ListItemPhotoBinding) : RecyclerView.ViewHolder(binding.root) {
        val imgPhoto = binding.imgPhoto
    }

    object PhotoComparator : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem == newItem
        }
    }

}