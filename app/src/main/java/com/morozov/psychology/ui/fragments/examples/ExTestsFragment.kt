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
import com.morozov.psychology.mvp.presenters.examples.ExTestsPresenter
import com.morozov.psychology.mvp.views.examples.ExTestsView
import com.morozov.psychology.ui.adapters.examples.ExTestAdapter
import kotlinx.android.synthetic.main.example_cards_layout.*
import kotlinx.android.synthetic.main.example_description_layout.*
import kotlinx.android.synthetic.main.example_test_layout.*

class ExTestsFragment: MvpAppCompatFragment(), ExTestsView {

    @InjectPresenter
    lateinit var mPresenter: ExTestsPresenter

    lateinit var mActivityPresenter: MainPresenter

    lateinit var adapter: ExTestAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.example_test_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ExTestAdapter()
        recyclerTest.layoutManager = LinearLayoutManager(context)
        recyclerTest.adapter = adapter

        buttonFinishTest.setOnClickListener {
            if (mActivityPresenter != null)
                mActivityPresenter.showExResults()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mPresenter.loadData()
    }

    override fun showData(data: List<String>) {
        adapter.setData(data)
    }
}