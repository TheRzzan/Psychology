package com.morozov.psychology.ui.adapters.settings.wallpaper

import android.arch.lifecycle.MutableLiveData
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.morozov.psychology.ui.adapters.listeners.OnItemClickListener
import kotlinx.android.synthetic.main.item_settings_wallpaper.view.*

class StgWallpaperViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun populate(drawable: Drawable, position: Int, selectedPosition : MutableLiveData<Int>, listener: OnItemClickListener) {
        itemView.imageItemStyleWallpaper.setImageDrawable(drawable)
        selectedPosition.observeForever {
            if (it == position)
                itemView.relativeWallpaperIsSelected.visibility = View.VISIBLE
            else
                itemView.relativeWallpaperIsSelected.visibility = View.GONE
        }
        itemView.setOnClickListener{
            listener.onItemClick(itemView.imageItemStyleWallpaper, position)
        }
    }
}