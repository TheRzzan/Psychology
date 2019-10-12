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
import com.morozov.psychology.utility.AppConstants
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

        buttonChoseAnother.setOnClickListener {
            if (mActivityPresenter != null)
                mActivityPresenter.showExCards()
        }

        buttonConsolidateScill.setOnClickListener {
            if (mActivityPresenter != null)
                mActivityPresenter.showExFixDescr(0)
        }
    }

    override fun onStart() {
        super.onStart()

        val bundle = this.arguments

        if (bundle != null)
            mPresenter.loadResult(bundle.getInt(AppConstants.EXP_POSITION))
        else
            mPresenter.loadResult(0)
    }

    /*
    * ExResultsView implementation
    *
    * */
    override fun showTitle(title: String) {
        textResultName.text = title
    }

    override fun showResult(result: String) {
        textResultDescription.text = result
    }

    override fun showButtonNext(text: String, position: Int) {
        buttonNextTest.visibility = View.VISIBLE
        buttonNextTest.text = text
        buttonNextTest.setOnClickListener {
            if (mActivityPresenter != null)
                mActivityPresenter.showExDescr(null, position)
        }
    }

    override fun hideButtonNext() {
        buttonNextTest.visibility = View.GONE
    }
}