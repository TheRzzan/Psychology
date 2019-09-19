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
import com.morozov.psychology.utility.AppConstants
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

        val bundle = this.arguments

        if (bundle != null)
            mPresenter.showFixing(bundle.getInt(AppConstants.EXP_POSITION))
        else
            mPresenter.showFixing(0)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = this.arguments

        buttonFixExit.setOnClickListener {
            activity?.onBackPressed()
        }

        buttonFixStartTest.setOnClickListener {
            if (mActivityPresenter != null) {
                if (bundle != null)
                    mActivityPresenter.showExFixTest(bundle.getInt(AppConstants.EXP_POSITION))
                else
                    mActivityPresenter.showExFixTest(0)
            }
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