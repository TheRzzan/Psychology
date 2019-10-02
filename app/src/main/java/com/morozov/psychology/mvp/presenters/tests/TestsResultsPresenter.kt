package com.morozov.psychology.mvp.presenters.tests

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.morozov.psychology.DefaultApplication
import com.morozov.psychology.domain.interfaces.tests.ResultsLoader
import com.morozov.psychology.mvp.views.tests.TestsResultsView
import javax.inject.Inject

@InjectViewState
class TestsResultsPresenter: MvpPresenter<TestsResultsView>() {

    @Inject
    lateinit var resultsLoader: ResultsLoader

    init {
        DefaultApplication.testsComponent.inject(this)
    }

    fun loadData(testName: String) {
        viewState.showResult(resultsLoader.getLastResult(testName).second!!.items)
        setShowNext()
    }

    fun setShowNext() {

    }

    fun showNextQuiz() {

    }
}