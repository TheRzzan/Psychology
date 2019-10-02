package com.morozov.psychology.ui.fragments.tests

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.psychology.DefaultApplication
import com.morozov.psychology.R
import com.morozov.psychology.domain.interfaces.tests.ResultsLoader
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.mvp.presenters.tests.TestsResultsPresenter
import com.morozov.psychology.mvp.views.tests.TestsResultsView
import com.morozov.psychology.ui.adapters.tests.results.TstResultsAdapter
import com.morozov.psychology.utility.AppConstants
import kotlinx.android.synthetic.main.tests_quiz_results_layout.*
import javax.inject.Inject

class TestsResultsFragment: MvpAppCompatFragment(), TestsResultsView {

    @Inject
    lateinit var resultsLoader: ResultsLoader

    @InjectPresenter
    lateinit var mPresenter: TestsResultsPresenter
    lateinit var mActivityPresenter: MainPresenter

    lateinit var adapter: TstResultsAdapter

    init {
        DefaultApplication.testsComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.tests_quiz_results_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = TstResultsAdapter()

        recyclerTestQuiz.layoutManager = LinearLayoutManager(context)
        recyclerTestQuiz.adapter = adapter

        adapter.setData(loadData())

        buttonNextQuiz.setOnClickListener {
            val bundle = this.arguments ?: return@setOnClickListener

            val name = bundle.getString(AppConstants.TEST_NAME) ?: return@setOnClickListener

            mActivityPresenter.showTestQuiz(name)
        }

        buttonChoseAnotherQuiz.setOnClickListener {
            mActivityPresenter.showTestSection()
        }

        buttonDiary.setOnClickListener {
            mActivityPresenter.showDiaryCards()
        }

    }

    private fun loadData(): List<Pair<String, String>> {
        val bundle = this.arguments ?: return listOf()
        val name = bundle.getString(AppConstants.TEST_NAME) ?: return listOf()

        return resultsLoader.getLastResult(name).second!!.items
    }

    /*
    * TestsResultsView implementation
    *
    * */
}