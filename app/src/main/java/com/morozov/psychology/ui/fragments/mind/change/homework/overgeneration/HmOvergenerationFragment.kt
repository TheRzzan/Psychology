package com.morozov.psychology.ui.fragments.mind.change.homework.overgeneration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.psychology.R
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.mvp.presenters.mind.change.homework.overgeneration.HmOvergenerationPresenter
import com.morozov.psychology.mvp.views.mind.change.homework.overgeneration.HmOvergenerationView
import kotlinx.android.synthetic.main.homework_overgeneration_layout.*

class HmOvergenerationFragment: MvpAppCompatFragment(), HmOvergenerationView {

    @InjectPresenter
    lateinit var mPresenter: HmOvergenerationPresenter
    lateinit var mActivityPresenter: MainPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.homework_overgeneration_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonAddNewThink.setOnClickListener {
            mActivityPresenter.showMindChangeSection()
        }

        buttonChooseAnother.setOnClickListener {
            mActivityPresenter.showHmMain()
        }
    }
}