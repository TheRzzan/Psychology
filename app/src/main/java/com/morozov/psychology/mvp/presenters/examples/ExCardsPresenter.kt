package com.morozov.psychology.mvp.presenters.examples

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.morozov.psychology.DefaultApplication
import com.morozov.psychology.domain.interfaces.examples.ExperimentsLoader
import com.morozov.psychology.mvp.views.examples.ExCardsView
import javax.inject.Inject

@InjectViewState
class ExCardsPresenter: MvpPresenter<ExCardsView>() {

    @Inject
    lateinit var experimentsLoader: ExperimentsLoader

    init {
        DefaultApplication.examplesComponent.inject(this)
    }

    fun loadDataExperiments() {
        viewState.showDataExperiments(experimentsLoader.getExperiments())
    }

    fun loadDataFixing() {
        viewState.showDataFixing(listOf("Упражнение на закрепление навыка поиска мыслей"))
    }
}