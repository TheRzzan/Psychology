package com.morozov.psychology.ui.fragments.tests

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.morozov.psychology.R
import com.morozov.psychology.mvp.presenters.MainPresenter
import kotlinx.android.synthetic.main.tests_all_results_layout.*

class TestsAllResultsFragment: Fragment() {

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
            mActivityPresenter.showTestAllResultsCards()
        }
        cardEllis.setOnClickListener {
            mActivityPresenter.showTestAllResultsCards()
        }
        cardHospitalScale.setOnClickListener {
            mActivityPresenter.showTestAllResultsCards()
        }
        cardIntegrative.setOnClickListener {
            mActivityPresenter.showTestAllResultsCards()
        }
        cardLazarusQuestionnaire.setOnClickListener {
            mActivityPresenter.showTestAllResultsCards()
        }
        cardSelfAttitude.setOnClickListener {
            mActivityPresenter.showTestAllResultsCards()
        }
        cardStyleIndex.setOnClickListener {
            mActivityPresenter.showTestAllResultsCards()
        }
    }
}