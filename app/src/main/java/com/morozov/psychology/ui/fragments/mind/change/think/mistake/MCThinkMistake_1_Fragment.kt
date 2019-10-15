package com.morozov.psychology.ui.fragments.mind.change.think.mistake

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.psychology.R
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.mvp.presenters.mind.change.think.mintake.MCThinkMistake_1_Presenter
import com.morozov.psychology.mvp.views.mind.change.think.mintake.MCThinkMistake_1_View
import com.morozov.psychology.ui.adapters.mind.change.think.mistake.MCThinkMistakeAdapter
import kotlinx.android.synthetic.main.mind_change_thinking_mistake_1_layout.*

class MCThinkMistake_1_Fragment: MvpAppCompatFragment(), MCThinkMistake_1_View {

    @InjectPresenter
    lateinit var mPresenter: MCThinkMistake_1_Presenter
    lateinit var mActivityPresenter: MainPresenter

    lateinit var adapter: MCThinkMistakeAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.mind_change_thinking_mistake_1_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonChoseAnother.setOnClickListener {
            mActivityPresenter.showMindChangeSection()
        }

        buttonReadyToChoose.setOnClickListener {

        }

        adapter = MCThinkMistakeAdapter()
        recyclerThinkMistake.adapter = adapter
        recyclerThinkMistake.layoutManager = LinearLayoutManager(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        context?.resources?.let { mPresenter.loadThinkMistakes(it) }
    }

    /*
    * MCThinkMistake_1_View implementation
    *
    * */
    override fun showThinkMistakes(data: List<Pair<Drawable, Pair<String, String>>>) {
        adapter.setData(data)
        adapter.notifyDataSetChanged()
    }
}