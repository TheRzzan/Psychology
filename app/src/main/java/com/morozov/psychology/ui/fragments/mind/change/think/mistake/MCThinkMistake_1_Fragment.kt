package com.morozov.psychology.ui.fragments.mind.change.think.mistake

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.psychology.R
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.mvp.presenters.mind.change.think.mintake.MCThinkMistake_1_Presenter
import com.morozov.psychology.mvp.views.mind.change.think.mintake.MCThinkMistake_1_View
import kotlinx.android.synthetic.main.mind_change_thinking_mistake_1_layout.*

class MCThinkMistake_1_Fragment: MvpAppCompatFragment(), MCThinkMistake_1_View {

    @InjectPresenter
    lateinit var mPresenter: MCThinkMistake_1_Presenter
    lateinit var mActivityPresenter: MainPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.mind_change_thinking_mistake_1_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonChoseAnother.setOnClickListener {
            mActivityPresenter.showMindChangeSection()
        }

        buttonReadyToChoose.setOnClickListener {

        }
    }

    /*
    * MCThinkMistake_1_View implementation
    *
    * */
    override fun showThinkMistakes(data: List<Pair<Drawable, Pair<String, String>>>) {

    }
}