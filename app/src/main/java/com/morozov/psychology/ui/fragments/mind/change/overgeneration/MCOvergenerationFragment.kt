package com.morozov.psychology.ui.fragments.mind.change.overgeneration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.psychology.R
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.mvp.presenters.mind.change.overgeneration.MCOvergenerationPresenter
import com.morozov.psychology.mvp.views.mind.change.overgeneration.MCOvergenerationView
import kotlinx.android.synthetic.main.mind_change_overgeneration_layout.*

class MCOvergenerationFragment: MvpAppCompatFragment(), MCOvergenerationView {

    @InjectPresenter
    lateinit var mPresenter: MCOvergenerationPresenter
    lateinit var mActivityPresenter: MainPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.mind_change_overgeneration_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonAddNewThink.setOnClickListener {
            mActivityPresenter.showMindChangeSection()
        }
    }
}