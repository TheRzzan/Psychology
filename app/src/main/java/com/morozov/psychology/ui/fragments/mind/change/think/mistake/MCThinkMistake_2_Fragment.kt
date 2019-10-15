package com.morozov.psychology.ui.fragments.mind.change.think.mistake

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.psychology.R
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.mvp.presenters.mind.change.think.mintake.MCThinkMistake_2_Presenter
import com.morozov.psychology.mvp.views.mind.change.think.mintake.MCThinkMistake_2_View

class MCThinkMistake_2_Fragment: MvpAppCompatFragment(), MCThinkMistake_2_View {

    @InjectPresenter
    lateinit var mPresenter: MCThinkMistake_2_Presenter
    lateinit var mActivityPresenter: MainPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.mind_change_thinking_mistake_2_layout, container, false)
}