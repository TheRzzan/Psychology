package com.morozov.psychology.ui.adapters.settings.wallpaper

import androidx.lifecycle.MutableLiveData
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.morozov.psychology.R
import com.morozov.psychology.ui.adapters.ListAdapter
import com.morozov.psychology.ui.adapters.listeners.OnItemClickListener

class StgWallpaperAdapter(private val listener: OnItemClickListener): ListAdapter<Drawable, StgWallpaperViewHolder>() {

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
        holder.populate(data()[position], position, selectedPosition, listener)
    }
}