package com.morozov.psychology.ui.fragments.tests

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
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

    @InjectPresenter
    lateinit var mPresenter: TestsResultsPresenter
    lateinit var mActivityPresenter: MainPresenter

    lateinit var adapter: TstResultsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.tests_quiz_results_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = this.arguments ?: return
        val name = bundle.getString(AppConstants.TEST_NAME) ?: return

        adapter = TstResultsAdapter()
        recyclerTestQuiz.layoutManager =
            androidx.recyclerview.widget.LinearLayoutManager(context)
        recyclerTestQuiz.adapter = adapter

        buttonNextQuiz.setOnClickListener {
            val nextTest = mPresenter.descriptionLoader.nextTest(name) ?: return@setOnClickListener
            showNext(nextTest)
        }

        buttonChoseAnotherQuiz.setOnClickListener {
            mActivityPresenter.showTestSection()
        }

        buttonDiary.setOnClickListener {
            mActivityPresenter.showDiaryCards()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = this.arguments ?: return
        val name = bundle.getString(AppConstants.TEST_NAME) ?: return

        mPresenter.loadData(name)
    }

    /*
    * TestsResultsView implementation
    *
    * */
    override fun showResult(data: List<Pair<String, String>>) {
        adapter.setData(data)
    }

    override fun showNext(testName: String) {
        mActivityPresenter.showTestDescr(testName)
    }

    override fun setVisibilityNextButton(b: Boolean) {
        buttonNextQuiz.visibility = when (b) {
            true -> View.VISIBLE
            false -> View.GONE
        }
    }
}