package com.morozov.psychology.ui.fragments.examples

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.psychology.R
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.mvp.presenters.examples.ExCardsPresenter
import com.morozov.psychology.mvp.views.examples.ExCardsView
import com.morozov.psychology.ui.adapters.examples.ExAdapter
import kotlinx.android.synthetic.main.example_cards_layout.*

class ExCardsFragment: MvpAppCompatFragment(), ExCardsView, View.OnClickListener {

    @InjectPresenter
    lateinit var mPresenter: ExCardsPresenter

    lateinit var mActivityPresenter: MainPresenter

    lateinit var adapter: ExAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.example_cards_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ExAdapter(this)
        recyclerCards.layoutManager = LinearLayoutManager(context)
        recyclerCards.adapter = adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mPresenter.loadData()
    }

    override fun onClick(v: View?) {
        if (v != null && v.id == R.id.imageCard && mActivityPresenter != null) {
            mActivityPresenter.showExDescr()
        }
    }

    override fun showData(data: List<String>) {
        adapter.setData(data)
    }
}
