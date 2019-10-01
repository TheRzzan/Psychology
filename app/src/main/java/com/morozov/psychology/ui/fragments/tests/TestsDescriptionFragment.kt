package com.morozov.psychology.ui.fragments.tests

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.morozov.psychology.R
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.utility.AppConstants
import kotlinx.android.synthetic.main.tests_description_layout.*

class TestsDescriptionFragment: Fragment() {

    lateinit var mActivityPresenter: MainPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.tests_description_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonTestsStart.setOnClickListener {
            val bundle = this.arguments ?: return@setOnClickListener

            val name = bundle.getString(AppConstants.TEST_NAME) ?: return@setOnClickListener

            mActivityPresenter.showTestQuiz(name)
        }
    }
}