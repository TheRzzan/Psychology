package com.morozov.psychology.ui.fragments.tests

import android.os.Bundle
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
import com.morozov.psychology.mvp.presenters.tests.TestsAllResultsCardsPresenter
import com.morozov.psychology.mvp.views.tests.TestsAllResultsCardsView
import com.morozov.psychology.ui.adapters.tests.all.results.TstAllResultsAdapter
import com.morozov.psychology.utility.AppConstants
import kotlinx.android.synthetic.main.tests_all_results_cards_layout.*
import java.text.SimpleDateFormat
import javax.inject.Inject

class TestsAllResultsCardsFragment: MvpAppCompatFragment(), TestsAllResultsCardsView {

    @InjectPresenter
    lateinit var mPresenter: TestsAllResultsCardsPresenter
    lateinit var mActivityPresenter: MainPresenter

    lateinit var adapter: TstAllResultsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.tests_all_results_cards_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = TstAllResultsAdapter()
        recyclerAllTestsResultsDays.layoutManager = LinearLayoutManager(context)
        recyclerAllTestsResultsDays.adapter = adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = this.arguments ?: return
        val name = bundle.getString(AppConstants.TEST_NAME) ?: return

        mPresenter.loadData(name)
    }

    /*
    * TestsAllResultsCardsView implementation
    *
    * */
    override fun showData(name: String, data: List<Pair<String, List<Pair<String, String>>>>) {
        textAllResultsTestName.text = name
        adapter.setData(data)
    }
}