package com.morozov.psychology.ui.fragments.tests

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.psychology.R
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.mvp.presenters.tests.TestsAboutPresenter
import com.morozov.psychology.mvp.views.tests.TestsAboutView

class TestsAboutFragment: MvpAppCompatFragment(), TestsAboutView {

    @InjectPresenter
    lateinit var mPresenter: TestsAboutPresenter
    lateinit var mActivityPresenter: MainPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.tests_about_layout, container, false)

    /*
    * TestsAboutView implementation
    *
    * */
}