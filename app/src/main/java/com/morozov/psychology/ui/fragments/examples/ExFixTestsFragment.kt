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
import com.morozov.psychology.mvp.presenters.examples.ExFixTestsPresenter
import com.morozov.psychology.mvp.views.examples.ExFixTestsView
import com.morozov.psychology.ui.adapters.examples.test.ExTestAdapter
import kotlinx.android.synthetic.main.example_fix_test_layout.*

class ExFixTestsFragment: MvpAppCompatFragment(), ExFixTestsView {

    /*
    * Moxy presenters
    *
    * */
    @InjectPresenter
    lateinit var mPresenter: ExFixTestsPresenter
    lateinit var mActivityPresenter: MainPresenter

    /*
    * Recycler adapter
    *
    * */
    lateinit var adapter: ExTestAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.example_fix_test_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ExTestAdapter()
        recyclerFixTest.layoutManager = LinearLayoutManager(context)
        recyclerFixTest.adapter = adapter

        buttonFixFinishTest.setOnClickListener {
            mPresenter.showResults()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mPresenter.loadData()
    }

    /*
    * ExFixTestsView implementation
    *
    * */
    override fun showData(data: List<String>) {
        adapter.setData(data)
    }

    override fun showResults() {
        scrollFixTest.smoothScrollTo(0, 0)
        buttonFixFinishTest.setOnClickListener {
            if (mActivityPresenter != null)
                mActivityPresenter.showExCards()
        }
        buttonFixFinishTest.text = "Далее"
        linearFixTestHint.visibility = View.VISIBLE
        textFixTestHint.visibility = View.GONE
    }
}