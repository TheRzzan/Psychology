package com.morozov.psychology.mvp.presenters.settings

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.morozov.psychology.R
import com.morozov.psychology.mvp.views.settings.SettingsWallpaperView

@InjectViewState
class SettingsWallpaperPresenter: MvpPresenter<SettingsWallpaperView>() {

    var data: List<Drawable> = listOf()
    var selectedPos = 0

    fun loadImages(context: Context) {
        if (data.isEmpty()) {
            data = listOf(
                ColorDrawable(context.resources.getColor(R.color.white)),
                context.getDrawable(R.drawable.wallpaper_1),
                context.getDrawable(R.drawable.wallpaper_2),
                context.getDrawable(R.drawable.wallpaper_3))
        }

        viewState.showImages(data)
    }

    fun showImage(position: Int) {
        selectedPos = position
        viewState.showMainImage(data[position].constantState.newDrawable().mutate(), position)
    }
}