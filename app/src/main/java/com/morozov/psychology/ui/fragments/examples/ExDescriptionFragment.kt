package com.morozov.psychology.ui.fragments.examples

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.psychology.R
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.mvp.presenters.examples.ExDescriptionPresenter
import com.morozov.psychology.mvp.views.examples.ExDescriptionView
import kotlinx.android.synthetic.main.example_description_layout.*

class ExDescriptionFragment: MvpAppCompatFragment(), ExDescriptionView {

    /*
    * Moxy presenters
    *
    * */
    @InjectPresenter
    lateinit var mPresenter: ExDescriptionPresenter
    lateinit var mActivityPresenter: MainPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.example_description_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonExit.setOnClickListener {
            if (mActivityPresenter != null)
                mActivityPresenter.showExCards()
        }

        buttonStartTest.setOnClickListener {
            if (mActivityPresenter != null)
                mActivityPresenter.showExTest()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mPresenter.loadData()
    }

    /*
    * ExDescriptionView implementation
    *
    * */
    override fun showData(name: String, description: String) {
        textDescrName.text = name
        textDescription.text = description
    }
}