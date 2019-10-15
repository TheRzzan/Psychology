package com.morozov.psychology.ui.fragments.mind.change

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.psychology.R
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.mvp.presenters.mind.change.MindChangeThinkTestPresenter
import com.morozov.psychology.mvp.views.mind.change.MindChangeThinkTestView

class MindChangeThinkTestFragment: MvpAppCompatFragment(), MindChangeThinkTestView {

    /*
    * Moxy presenters
    *
    * */
    @InjectPresenter
    lateinit var mPresenter: MindChangeThinkTestPresenter
    lateinit var mActivityPresenter: MainPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.mind_change_think_test_layout, container, false)
}