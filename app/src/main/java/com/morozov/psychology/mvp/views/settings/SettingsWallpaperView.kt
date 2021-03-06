package com.morozov.psychology.mvp.views.settings

import android.graphics.drawable.Drawable
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface SettingsWallpaperView: MvpView {

    fun showMainImage(drawable: Drawable, position: Int)

    fun showImages(data: List<Drawable>)
}