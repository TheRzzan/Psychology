package com.morozov.psychology.ui.fragments.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.psychology.R
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.mvp.presenters.settings.SettingsPresenter
import com.morozov.psychology.mvp.views.settings.SettingsView
import kotlinx.android.synthetic.main.settings_main_layout.*

class SettingsFragment: MvpAppCompatFragment(), SettingsView {

    @InjectPresenter
    lateinit var mPresenter: SettingsPresenter
    lateinit var mActivityPresenter: MainPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.settings_main_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        relativeStyle.setOnClickListener {
            mActivityPresenter.showSettingsStylesSection()
        }

        relativeWallpaper.setOnClickListener {
            mActivityPresenter.showSettingsWallpaper()
        }
    }
}