package com.morozov.psychology.ui.adapters.settings.wallpaper

import android.arch.lifecycle.MutableLiveData
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.morozov.psychology.R
import com.morozov.psychology.ui.adapters.ListAdapter
import com.morozov.psychology.ui.adapters.listeners.OnItemClickListener

class StgWallpaperAdapter: ListAdapter<Drawable, StgWallpaperViewHolder>(), OnItemClickListener {

    var selectedPosition : MutableLiveData<Int> = MutableLiveData()

    override fun onCreateViewHolder(container: ViewGroup, p1: Int): StgWallpaperViewHolder =
        StgWallpaperViewHolder(
            LayoutInflater.from(container.context).inflate(
                R.layout.item_settings_wallpaper,
                container,
                false
            )
        )

    override fun onBindViewHolder(holder: StgWallpaperViewHolder, position: Int) {
        if (selectedPosition.value == null)
            holder.populate(data()[position], position, position == 0, this)
        else
            holder.populate(data()[position], position, position == selectedPosition.value, this)
    }

    /*
    * OnItemClickListener implementation
    *
    * */
    override fun onItemClick(view: View, position: Int) {
        selectedPosition.value = position
        notifyDataSetChanged()
    }
}