package com.morozov.psychology.mvp.presenters.tests

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.morozov.psychology.DefaultApplication
import com.morozov.psychology.domain.interfaces.tests.DescriptionLoader
import com.morozov.psychology.domain.interfaces.tests.ResultsLoader
import com.morozov.psychology.mvp.views.tests.TestsResultsView
import javax.inject.Inject

@InjectViewState
class TestsResultsPresenter: MvpPresenter<TestsResultsView>() {

    @Inject
    lateinit var resultsLoader: ResultsLoader

    @Inject
    lateinit var descriptionLoader: DescriptionLoader

    init {
        DefaultApplication.testsComponent.inject(this)
    }

    fun loadData(testName: String) {
        viewState.showResult(resultsLoader.getLastResult(testName).second!!.items)
        setShowNext(descriptionLoader.nextTest(testName))
    }

    private fun setShowNext(name: String?) {
        viewState.setVisibilityNextButton(name != null)
    }
}