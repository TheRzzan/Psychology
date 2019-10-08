package com.morozov.psychology.ui.adapters.settings.wallpaper

import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.View
import com.morozov.psychology.ui.adapters.listeners.OnItemClickListener
import kotlinx.android.synthetic.main.item_settings_wallpaper.view.*

class StgWallpaperViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun populate(drawable: Drawable, position: Int, b: Boolean) {
        itemView.imageItemStyleWallpaper.setImageDrawable(drawable)
        if (b)
            itemView.relativeWallpaperIsSelected.visibility = View.VISIBLE
        else
            itemView.relativeWallpaperIsSelected.visibility = View.GONE
    }
}