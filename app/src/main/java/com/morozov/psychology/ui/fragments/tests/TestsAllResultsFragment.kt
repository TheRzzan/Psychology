package com.morozov.psychology.ui.fragments.tests

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.psychology.R
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.mvp.presenters.tests.TestsAllResultsPresenter
import com.morozov.psychology.mvp.views.tests.TestsAllResultsView
import com.morozov.psychology.utility.AppConstants
import kotlinx.android.synthetic.main.tests_all_results_layout.*

class TestsAllResultsFragment: MvpAppCompatFragment(), TestsAllResultsView {

    @InjectPresenter
    lateinit var mPresenter: TestsAllResultsPresenter
    lateinit var mActivityPresenter: MainPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.tests_all_results_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setCardsOnClick()
    }

    /*
    * Helper functions
    *
    * */
    private fun setCardsOnClick() {
        cardWeismanBack.setOnClickListener {
            mActivityPresenter.showTestAllResultsCards(AppConstants.WEISMAN_BACK_TEST)
        }
        cardEllis.setOnClickListener {
            mActivityPresenter.showTestAllResultsCards(AppConstants.ELLIS_TEST)
        }
        cardHospitalScale.setOnClickListener {
            mActivityPresenter.showTestAllResultsCards(AppConstants.HOSPITAL_SCALE_TEST)
        }
        cardIntegrative.setOnClickListener {
            mActivityPresenter.showTestAllResultsCards(AppConstants.INTEGRATIVE_TEST)
        }
        cardLazarusQuestionnaire.setOnClickListener {
            mActivityPresenter.showTestAllResultsCards(AppConstants.LAZARUS_QUESTIONNAIRE_TEST)
        }
        cardSelfAttitude.setOnClickListener {
            mActivityPresenter.showTestAllResultsCards(AppConstants.SELF_ATTITUDE_TEST)
        }
        cardStyleIndex.setOnClickListener {
            mActivityPresenter.showTestAllResultsCards(AppConstants.STYLE_INDEX_TEST)
        }
    }

    /*
    * TestsAllResultsView implementation
    *
    * */
}