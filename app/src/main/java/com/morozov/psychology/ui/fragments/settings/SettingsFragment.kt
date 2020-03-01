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
import android.content.Intent
import android.net.Uri

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

        relativeConsult.setOnClickListener {
            mActivityPresenter.showSettingsConsult()
        }

        relativeAbout.setOnClickListener {
            mActivityPresenter.showAboutApplication()
        }

        relativeEstimate.setOnClickListener {
            val appPackageName = activity?.packageName ?: return@setOnClickListener //"com.plarium.raidlegends"
            try {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appPackageName")))
            } catch (anfe: android.content.ActivityNotFoundException) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
                    )
                )
            }

        }
    }
}