package com.morozov.psychology.mvp.presenters.settings

import android.content.Context
import android.graphics.drawable.Drawable
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.morozov.psychology.R
import com.morozov.psychology.mvp.views.settings.SettingsWallpaperView

@InjectViewState
class SettingsWallpaperPresenter: MvpPresenter<SettingsWallpaperView>() {

    private var data: List<Drawable> = listOf()

    fun loadImages(context: Context) {
        if (data.isEmpty()) {
            data = listOf(context.getDrawable(R.drawable.image_test_1),
                context.getDrawable(R.drawable.image_test_2),
                context.getDrawable(R.drawable.image_test_2))
        }

        viewState.showImages(data)
    }

    fun showImage(position: Int) {
        viewState.showMainImage(data[position], position)
    }
}