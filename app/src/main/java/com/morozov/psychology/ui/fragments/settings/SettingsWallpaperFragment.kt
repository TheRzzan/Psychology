package com.morozov.psychology.ui.fragments.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.psychology.R
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.mvp.presenters.settings.SettingsWallpaperPresenter
import com.morozov.psychology.mvp.views.settings.SettingsWallpaperView

class SettingsWallpaperFragment: MvpAppCompatFragment(), SettingsWallpaperView {

    @InjectPresenter
    lateinit var mPresenter: SettingsWallpaperPresenter
    lateinit var mActivityPresenter: MainPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.settings_wallpaper_layout, container, false)
}