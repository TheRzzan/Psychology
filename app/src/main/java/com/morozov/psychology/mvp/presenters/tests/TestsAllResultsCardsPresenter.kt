package com.morozov.psychology.mvp.presenters.tests

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.morozov.psychology.DefaultApplication
import com.morozov.psychology.domain.interfaces.tests.ResultsLoader
import com.morozov.psychology.mvp.views.tests.TestsAllResultsCardsView
import java.text.SimpleDateFormat
import javax.inject.Inject

@InjectViewState
class TestsAllResultsCardsPresenter: MvpPresenter<TestsAllResultsCardsView>() {

    @Inject
    lateinit var resultsLoader: ResultsLoader

    init {
        DefaultApplication.testsComponent.inject(this)
    }

    fun loadData(testName: String) {
        val pair = resultsLoader.getAllResults(testName)
        val allResults = pair.second
        var listTmp = mutableListOf<Pair<String, List<Pair<String, String>>>>()

        val dayMtYrFormat = SimpleDateFormat("dd.MM.yyyy")

        for (item in allResults) {
            listTmp.add(Pair(dayMtYrFormat.format(item.date), item.items))
        }

        viewState.showData(pair.first, listTmp)
    }
}