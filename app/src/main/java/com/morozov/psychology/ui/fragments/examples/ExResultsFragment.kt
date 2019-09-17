package com.morozov.psychology.ui.fragments.examples

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.psychology.R
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.mvp.presenters.examples.ExResultsPresenter
import com.morozov.psychology.mvp.views.examples.ExResultsView
import kotlinx.android.synthetic.main.example_result_layout.*

class ExResultsFragment: MvpAppCompatFragment(), ExResultsView {

    /*
    * Moxy presenters
    *
    * */
    @InjectPresenter
    lateinit var mPresenter: ExResultsPresenter
    lateinit var mActivityPresenter: MainPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.example_result_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonNextTest.setOnClickListener {
            if (mActivityPresenter != null)
                mActivityPresenter.showExTest()
        }

        buttonChoseAnother.setOnClickListener {
            if (mActivityPresenter != null)
                mActivityPresenter.showExCards()
        }
    }
}