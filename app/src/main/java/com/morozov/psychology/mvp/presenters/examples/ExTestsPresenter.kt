package com.morozov.psychology.mvp.presenters.examples

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.morozov.psychology.DefaultApplication
import com.morozov.psychology.domain.interfaces.examples.ExperimentsLoader
import com.morozov.psychology.mvp.views.examples.ExTestsView
import javax.inject.Inject

@InjectViewState
class ExTestsPresenter: MvpPresenter<ExTestsView>() {

    @Inject
    lateinit var experimentsLoader: ExperimentsLoader

    init {
        DefaultApplication.examplesComponent.inject(this)
    }

    fun loadData(position: Int) {
        val experiments = experimentsLoader.getExperiments()

        viewState.showTitle(experiments[position].title)
        viewState.showDescription(experiments[position].description)
        viewState.showVariants(experiments[position].variants)
    }
}