package com.morozov.psychology.ui.fragments.mind.change.black.white

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.psychology.R
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.mvp.presenters.mind.change.black.white.MCBlackWhitePresenter
import com.morozov.psychology.mvp.views.mind.change.black.white.MCBlackWhiteView
import com.morozov.psychology.ui.adapters.mind.change.aback.white.MCBlackWhiteAdapter
import kotlinx.android.synthetic.main.mind_change_black_and_white_layout.*

class MCBlackWhiteFragment: MvpAppCompatFragment(), MCBlackWhiteView {

    @InjectPresenter
    lateinit var mPresenter: MCBlackWhitePresenter
    lateinit var mActivityPresenter: MainPresenter

    lateinit var adapter: MCBlackWhiteAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.mind_change_black_and_white_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonAddNewThink.setOnClickListener {
            mActivityPresenter.showMindChangeSection()
        }

        adapter = MCBlackWhiteAdapter(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
        recyclerBW.adapter = adapter
        recyclerBW.layoutManager = LinearLayoutManager(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mPresenter.loadData()
    }

    /*
    * MCBlackWhiteView implementation
    *
    * */
    override fun showRecycler(data: List<String>) {
        adapter.setData(data)
        adapter.notifyDataSetChanged()
    }

    override fun showThink(situation: String, newThink: String) {
        editBWSituation.setText(situation)
        editBWThink.setText(newThink)
    }
}