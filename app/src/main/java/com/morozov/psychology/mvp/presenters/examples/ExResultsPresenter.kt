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

    fun loadResult(position: Int) {
        val experiments = experimentsLoader.getExperiments()

        viewState.showTitle(experiments[position].title)
        viewState.showResult(experiments[position].conclusion)

        if (position + 1 >= experiments.size)
            viewState.hideButtonNext()
        else
            viewState.showButtonNext(experiments[position + 1].title, position + 1)
    }
}