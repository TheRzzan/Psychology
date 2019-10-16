package com.morozov.psychology.ui.fragments.mind.change.deprecation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.psychology.R
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.mvp.presenters.mind.change.deprecation.MCDeprecation_3_Presenter
import com.morozov.psychology.mvp.views.mind.change.deprecation.MCDeprecation_3_View
import kotlinx.android.synthetic.main.mind_change_depreciation_3_layout.*

class MCDeprecation_3_Fragment: MvpAppCompatFragment(), MCDeprecation_3_View {

    @InjectPresenter
    lateinit var mPresenter: MCDeprecation_3_Presenter
    lateinit var mActivityPresenter: MainPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.mind_change_depreciation_3_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonAddNewThink.setOnClickListener {
            mActivityPresenter.showMindChangeSection()
        }
    }
}