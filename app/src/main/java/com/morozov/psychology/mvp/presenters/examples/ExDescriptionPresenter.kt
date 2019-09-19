package com.morozov.psychology.mvp.presenters.examples

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.morozov.psychology.DefaultApplication
import com.morozov.psychology.domain.interfaces.examples.ExperimentsLoader
import com.morozov.psychology.mvp.views.examples.ExDescriptionView
import javax.inject.Inject

@InjectViewState
class ExDescriptionPresenter: MvpPresenter<ExDescriptionView>() {

    @Inject
    lateinit var experimentsLoader: ExperimentsLoader

    init {
        DefaultApplication.examplesComponent.inject(this)
    }

    fun loadData() {
        val experiments = experimentsLoader.getExperiments()

        viewState.showData(experiments[experiments.size - 1].title,
            experiments[experiments.size - 1].description)
    }
}