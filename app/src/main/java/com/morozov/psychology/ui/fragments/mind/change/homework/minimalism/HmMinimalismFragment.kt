package com.morozov.psychology.ui.fragments.mind.change.homework.minimalism

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.psychology.R
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.mvp.presenters.mind.change.homework.minimalism.HmMinimalismPresenter
import com.morozov.psychology.mvp.views.mind.change.homework.minimalism.HmMinimalismView
import kotlinx.android.synthetic.main.homework_minimalism_layout.*

class HmMinimalismFragment: MvpAppCompatFragment(), HmMinimalismView {

    @InjectPresenter
    lateinit var mPresenter: HmMinimalismPresenter
    lateinit var mActivityPresenter: MainPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.homework_minimalism_layout, container, false)

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