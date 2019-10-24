package com.morozov.psychology.ui.fragments.mind.change.homework.mind.reading

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.psychology.R
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.mvp.presenters.mind.change.homework.mind.reading.HmMindReading_1_Presenter
import com.morozov.psychology.mvp.views.mind.change.homework.mind.reading.HmMindReading_1_View
import kotlinx.android.synthetic.main.homework_mind_reading_1_layout.*

class HmMindReading_1_Fragment: MvpAppCompatFragment(), HmMindReading_1_View {

    @InjectPresenter
    lateinit var mPresenter: HmMindReading_1_Presenter
    lateinit var mActivityPresenter: MainPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.homework_mind_reading_1_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonNext.setOnClickListener {
            mActivityPresenter.showHmMindReading_2()
        }
    }
}