package com.morozov.psychology.ui.fragments.examples

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.psychology.R
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.mvp.presenters.examples.ExFixDescriptionPresenter
import com.morozov.psychology.mvp.views.examples.ExFixDescriptionView
import kotlinx.android.synthetic.main.example_fix_description_layout.*

class ExFixDescriptionFragment: MvpAppCompatFragment(), ExFixDescriptionView {

    /*
    * Moxy presenters
    *
    * */
    @InjectPresenter
    lateinit var mPresenter: ExFixDescriptionPresenter
    lateinit var mActivityPresenter: MainPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.example_fix_description_layout, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mPresenter.loadData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fabFixExit.setOnClickListener {
            if (mActivityPresenter != null)
                mActivityPresenter.showExCards()
        }

        buttonFixStartTest.setOnClickListener {
            if (mActivityPresenter != null)
                mActivityPresenter.showExFixTest()
        }
    }

    /*
    * ExDescriptionView implementation
    *
    * */
    override fun showData(name: String, description: String) {
        textFixDescrName.text = name
        textFixDescription.text = description
    }
}