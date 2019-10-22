package com.morozov.psychology.ui.fragments.settings

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.psychology.R
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.mvp.presenters.settings.SettingsStylePresenter
import com.morozov.psychology.mvp.views.settings.SettingsStyleView
import com.morozov.psychology.ui.adapters.settings.styles.StgStyleAdapter
import com.morozov.psychology.utility.AppConstants
import com.morozov.psychology.utility.MySharedPreferences
import kotlinx.android.synthetic.main.settings_style_layout.*

class SettingsStyleFragment: MvpAppCompatFragment(), SettingsStyleView {

    @InjectPresenter
    lateinit var mPresenter: SettingsStylePresenter
    lateinit var mActivityPresenter: MainPresenter

    private lateinit var adapter: StgStyleAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.settings_style_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = StgStyleAdapter()
        recyclerStyles.layoutManager = LinearLayoutManager(context)
        recyclerStyles.adapter = adapter

        adapter.selectedPosition.observeForever {
            if (it == null)
                return@observeForever

            buttonStyleSave.setBackgroundResource(R.drawable.rectangle_button)
            buttonStyleSave.isEnabled = true
        }

        adapter.selectedPosition.observeForever {
            when (it) {
                0 -> context?.let { it1 -> MySharedPreferences.setPreference(it1, AppConstants.PREF_PROG_STYLE, AppConstants.PREF_COLOR_DEFAULT) }
                1 -> context?.let { it1 -> MySharedPreferences.setPreference(it1, AppConstants.PREF_PROG_STYLE, AppConstants.PREF_COLOR_GREEN) }
                2 -> context?.let { it1 -> MySharedPreferences.setPreference(it1, AppConstants.PREF_PROG_STYLE, AppConstants.PREF_COLOR_TURQUOISE) }
                3 -> context?.let { it1 -> MySharedPreferences.setPreference(it1, AppConstants.PREF_PROG_STYLE, AppConstants.PREF_COLOR_BLUE) }
                4 -> context?.let { it1 -> MySharedPreferences.setPreference(it1, AppConstants.PREF_PROG_STYLE, AppConstants.PREF_COLOR_YELLOW) }
                5 -> context?.let { it1 -> MySharedPreferences.setPreference(it1, AppConstants.PREF_PROG_STYLE, AppConstants.PREF_COLOR_ORANGE) }
                6 -> context?.let { it1 -> MySharedPreferences.setPreference(it1, AppConstants.PREF_PROG_STYLE, AppConstants.PREF_COLOR_RED) }
            }
        }

        buttonStyleSave.setOnClickListener {
            mActivityPresenter.refreshActivity()
        }
    }

    override fun onStart() {
        super.onStart()

        context?.let { mPresenter.loadColors(it) }
    }

    /*
    * SettingsStyleView implementation
    *
    * */
    override fun showColors(data: List<Pair<String, Int>>) {
        adapter.setData(data)
    }
}