package com.morozov.psychology.ui.fragments.tests

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.morozov.psychology.DefaultApplication
import com.morozov.psychology.R
import com.morozov.psychology.domain.interfaces.tests.ResultsLoader
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.ui.adapters.tests.all.results.TstAllResultsAdapter
import com.morozov.psychology.utility.AppConstants
import kotlinx.android.synthetic.main.tests_all_results_cards_layout.*
import java.text.SimpleDateFormat
import javax.inject.Inject

class TestsAllResultsCardsFragment: Fragment() {

    @Inject
    lateinit var resultsLoader: ResultsLoader

    lateinit var mActivityPresenter: MainPresenter

    lateinit var adapter: TstAllResultsAdapter

    init {
        DefaultApplication.testsComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.tests_all_results_cards_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = TstAllResultsAdapter()
        recyclerAllTestsResultsDays.layoutManager = LinearLayoutManager(context)
        recyclerAllTestsResultsDays.adapter = adapter
        adapter.setData(getData())
    }

    private fun getData(): List<Pair<String, List<Pair<String, String>>>> {
        val bundle = this.arguments ?: return listOf()
        val name = bundle.getString(AppConstants.TEST_NAME) ?: return listOf()

        val allResults = resultsLoader.getAllResults(name)
        var listTmp = mutableListOf<Pair<String, List<Pair<String, String>>>>()

        val dayMtYrFormat = SimpleDateFormat("dd.MM.yyyy")

        for (item in allResults) {
            listTmp.add(Pair(dayMtYrFormat.format(item.date), item.items))
        }

        return listTmp
    }
}