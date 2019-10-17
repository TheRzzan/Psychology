package com.morozov.psychology.ui.fragments.mind.change.deprecation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.psychology.R
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.mvp.presenters.mind.change.deprecation.MCDeprecation_1_Presenter
import com.morozov.psychology.mvp.views.mind.change.deprecation.MCDeprecation_1_View
import kotlinx.android.synthetic.main.mind_change_depreciation_1_layout.*

class MCDeprecation_1_Fragment: MvpAppCompatFragment(), MCDeprecation_1_View {

    @InjectPresenter
    lateinit var mPresenter: MCDeprecation_1_Presenter
    lateinit var mActivityPresenter: MainPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.mind_change_depreciation_1_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonNext.setOnClickListener {
            mActivityPresenter.showMCDeprecation_2()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mPresenter.loadData()
    }

    /*
    * MCDeprecation_1_View implementation
    *
    * */
    override fun showThink(situation: String, newThink: String) {
        editDepSituation.setText(situation)
        editDepThink.setText(newThink)
    }
}