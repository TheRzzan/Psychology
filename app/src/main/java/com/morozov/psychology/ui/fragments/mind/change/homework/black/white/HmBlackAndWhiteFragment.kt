package com.morozov.psychology.ui.fragments.mind.change.homework.black.white

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.psychology.R
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.mvp.presenters.mind.change.homework.black.white.HmBlackAndWhitePresenter
import com.morozov.psychology.mvp.views.mind.change.homework.black.white.HmBlackAndWhiteView
import kotlinx.android.synthetic.main.homework_black_and_white_layout.*

class HmBlackAndWhiteFragment: MvpAppCompatFragment(), HmBlackAndWhiteView {

    @InjectPresenter
    lateinit var mPresenter: HmBlackAndWhitePresenter
    lateinit var mActivityPresenter: MainPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.homework_black_and_white_layout, container, false)

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