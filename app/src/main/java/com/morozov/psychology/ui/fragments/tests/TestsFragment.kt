package com.morozov.psychology.ui.fragments.tests

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.psychology.R
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.mvp.presenters.tests.TestsPresenter
import com.morozov.psychology.mvp.views.tests.TestsView
import com.morozov.psychology.utility.AppConstants
import kotlinx.android.synthetic.main.tests_cards_layout.*

class TestsFragment: MvpAppCompatFragment(), TestsView {

    @InjectPresenter
    lateinit var mPresenter: TestsPresenter
    lateinit var mActivityPresenter: MainPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.tests_cards_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonTestAbout.setOnClickListener {
            mActivityPresenter.showTestAbout()
        }

        buttonTestResults.setOnClickListener {
            mActivityPresenter.showTestAllResults()
        }

        setCardsOnClick()
    }

    /*
    * Helper functions
    *
    * */
    private fun setCardsOnClick() {
        cardWeismanBack.setOnClickListener {
            mActivityPresenter.showTestDescr(AppConstants.WEISMAN_BACK_TEST)
        }
        cardEllis.setOnClickListener {
            mActivityPresenter.showTestDescr(AppConstants.ELLIS_TEST)
        }
        cardHospitalScale.setOnClickListener {
            mActivityPresenter.showTestDescr(AppConstants.HOSPITAL_SCALE_TEST)
        }
        cardIntegrative.setOnClickListener {
            mActivityPresenter.showTestDescr(AppConstants.INTEGRATIVE_TEST)
        }
        cardLazarusQuestionnaire.setOnClickListener {
            mActivityPresenter.showTestDescr(AppConstants.LAZARUS_QUESTIONNAIRE_TEST)
        }
        cardSelfAttitude.setOnClickListener {
            mActivityPresenter.showTestDescr(AppConstants.SELF_ATTITUDE_TEST)
        }
        cardStyleIndex.setOnClickListener {
            mActivityPresenter.showTestDescr(AppConstants.STYLE_INDEX_TEST)
        }
    }

    /*
    * TestsView implementation
    *
    * */
}