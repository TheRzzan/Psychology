package com.morozov.psychology.ui.fragments.examples

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.psychology.R
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.mvp.presenters.examples.ExFixResultsPresenter
import com.morozov.psychology.mvp.presenters.examples.ExResultsPresenter
import com.morozov.psychology.mvp.views.examples.ExFixResultsView
import kotlinx.android.synthetic.main.example_fix_result_layout.*
import kotlinx.android.synthetic.main.example_result_layout.*

class ExFixResultsFragment: MvpAppCompatFragment(), ExFixResultsView {

    /*
    * Moxy presenters
    *
    * */
    @InjectPresenter
    lateinit var mPresenter: ExFixResultsPresenter
    lateinit var mActivityPresenter: MainPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.example_fix_result_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonFixDiary.setOnClickListener {
            if (mActivityPresenter != null)
                mActivityPresenter.showDiaryCards()
        }

        buttonFixChooseExperiment.setOnClickListener {
            if (mActivityPresenter != null)
                mActivityPresenter.showExCards()
        }
    }

    /*
    * ExResultsView implementation
    *
    * */
    override fun showTitle(title: String) {
        textFixResultName.text = title
    }

    override fun showResult(result: String) {
        textFixResultDescription.text = result
    }

    override fun showButtonNext(text: String, position: Int) {
        buttonFixNext.visibility = View.VISIBLE
        buttonFixNext.text = text
        buttonFixNext.setOnClickListener {

        }
    }

    override fun hideButtonNext() {
        buttonFixNext.visibility = View.GONE
    }
}