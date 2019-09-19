package com.morozov.psychology.mvp.presenters.examples

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.morozov.psychology.DefaultApplication
import com.morozov.psychology.domain.interfaces.examples.ExperimentsLoader
import com.morozov.psychology.mvp.views.examples.ExResultsView
import javax.inject.Inject

@InjectViewState
class ExResultsPresenter: MvpPresenter<ExResultsView>() {

    @Inject
    lateinit var experimentsLoader: ExperimentsLoader

    init {
        DefaultApplication.examplesComponent.inject(this)
    }

    fun loadResult() {
        val experiments = experimentsLoader.getExperiments()

        viewState.showTitle(experiments[experiments.size - 1].title)
        viewState.showResult(experiments[experiments.size - 1].conclusion)
    }
}