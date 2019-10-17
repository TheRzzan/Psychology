package com.morozov.psychology.ui.fragments.mind.change.emotional

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.psychology.R
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.mvp.presenters.mind.change.emotional.MCEmotionalPresenter
import com.morozov.psychology.mvp.views.mind.change.emotional.MCEmotionalView
import kotlinx.android.synthetic.main.mind_change_emotional_layout.*

class MCEmotionalFragment: MvpAppCompatFragment(), MCEmotionalView {

    @InjectPresenter
    lateinit var mPresenter: MCEmotionalPresenter
    lateinit var mActivityPresenter: MainPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.mind_change_emotional_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonAddNewThink.setOnClickListener {
            mActivityPresenter.showMindChangeSection()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mPresenter.loadData()
    }

    /*
    * MCEmotionalView implementation
    *
    * */
    override fun showThink(situation: String, newThink: String) {
        editEmotSituation.setText(situation)
        editEmotThink.setText(newThink)
    }
}