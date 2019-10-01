package com.morozov.psychology.mvp.presenters.tests

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.morozov.psychology.DefaultApplication
import com.morozov.psychology.domain.interfaces.tests.DescriptionLoader
import com.morozov.psychology.mvp.views.tests.TestsDescriptionView
import javax.inject.Inject

@InjectViewState
class TestsDescriptionPresenter: MvpPresenter<TestsDescriptionView>() {

    @Inject
    lateinit var descriptionLoader: DescriptionLoader

    init {
        DefaultApplication.testsComponent.inject(this)
    }

    fun loadData(testName: String) {
        viewState.showDescription(descriptionLoader.getDescription(testName).first,
            descriptionLoader.getDescription(testName).second)
    }
}