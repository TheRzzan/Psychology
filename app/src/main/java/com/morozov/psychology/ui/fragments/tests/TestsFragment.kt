package com.morozov.psychology.ui.fragments.tests

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.morozov.psychology.R
import com.morozov.psychology.mvp.presenters.MainPresenter
import kotlinx.android.synthetic.main.tests_cards_layout.*

class TestsFragment: Fragment() {

    lateinit var mActivityPresenter: MainPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.tests_cards_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonTestAbout.setOnClickListener {
            mActivityPresenter.showTestAbout()
        }

        setCardsOnClick()
    }

    /*
    * Helper functions
    *
    * */
    private fun setCardsOnClick() {
        cardWeismanBack.setOnClickListener {
            mActivityPresenter.showTestDescr()
        }
        cardEllis.setOnClickListener {
            mActivityPresenter.showTestDescr()
        }
        cardHospitalScale.setOnClickListener {
            mActivityPresenter.showTestDescr()
        }
        cardIntegrative.setOnClickListener {
            mActivityPresenter.showTestDescr()
        }
        cardLazarusQuestionnaire.setOnClickListener {
            mActivityPresenter.showTestDescr()
        }
        cardSelfAttitude.setOnClickListener {
            mActivityPresenter.showTestDescr()
        }
        cardStyleIndex.setOnClickListener {
            mActivityPresenter.showTestDescr()
        }
    }
}