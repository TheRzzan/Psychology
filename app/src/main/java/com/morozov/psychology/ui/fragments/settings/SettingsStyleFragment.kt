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