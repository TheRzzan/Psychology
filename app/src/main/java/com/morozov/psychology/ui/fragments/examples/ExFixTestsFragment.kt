package com.morozov.psychology.ui.fragments.examples

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.psychology.R
import com.morozov.psychology.mvp.models.ExFixingResultModel
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.mvp.presenters.examples.ExFixTestsPresenter
import com.morozov.psychology.mvp.views.examples.ExFixTestsView
import com.morozov.psychology.ui.adapters.examples.results.ExFixResultAdapter
import com.morozov.psychology.ui.adapters.examples.test.ExTestAdapter
import com.morozov.psychology.ui.adapters.listeners.OnTextChangeListener
import kotlinx.android.synthetic.main.example_fix_test_layout.*

class ExFixTestsFragment: MvpAppCompatFragment(), ExFixTestsView, OnTextChangeListener {

    /*
    * Moxy presenters
    *
    * */
    @InjectPresenter
    lateinit var mPresenter: ExFixTestsPresenter
    lateinit var mActivityPresenter: MainPresenter

    var allTextRecycler: MutableList<Boolean> = mutableListOf()

    /*
    * Recycler adapterTest
    *
    * */
    lateinit var adapterTest: ExTestAdapter
    lateinit var adapterResults: ExFixResultAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.example_fix_test_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapterTest = ExTestAdapter(this)
        recyclerFixTest.layoutManager = LinearLayoutManager(context)
        recyclerFixTest.adapter = adapterTest

        buttonFixFinishTest.setOnClickListener {
            mPresenter.showResults()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mPresenter.loadData()
    }

    /*
    * OnTextChangeListener implementation
    *
    * */
    override fun onTextChanged(position: Int, count: Int, symbolSet: String) {
        allTextRecycler[position] = count > 0

        var b = true
        for (item in allTextRecycler) {
            if (!item) {
                b = false
                break
            }
        }

        setFinishEnabled(b)
    }

    /*
    * ExFixTestsView implementation
    *
    * */
    override fun showData(data: List<String>) {
        adapterTest.setData(data)

        allTextRecycler = mutableListOf()

        var i = 0
        while (i < adapterTest.itemCount) {
            i++
            allTextRecycler.add(false)
        }

        setFinishEnabled(false)
    }

    override fun showResults(data: List<ExFixingResultModel>) {
        adapterResults = ExFixResultAdapter()
        recyclerFixTest.layoutManager = LinearLayoutManager(context)
        recyclerFixTest.adapter = adapterResults

        adapterResults.setData(data)

        scrollFixTest.smoothScrollTo(0, 0)

        buttonFixFinishTest.setOnClickListener {
            if (mActivityPresenter != null)
                mActivityPresenter.showExCards()
        }

        buttonFixFinishTest.text = "Далее"

        linearFixTestHint.visibility = View.VISIBLE
        textFixTestHint.visibility = View.GONE
    }

    override fun setFinishEnabled(boolean: Boolean) {
        buttonFixFinishTest.visibility = when(boolean) {
            true -> View.VISIBLE
            false -> View.GONE
        }
    }
}