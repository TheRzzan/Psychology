package com.morozov.psychology.mvp.presenters.examples

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.morozov.psychology.DefaultApplication
import com.morozov.psychology.domain.interfaces.examples.ExperimentsLoader
import com.morozov.psychology.domain.interfaces.examples.FixingLoader
import com.morozov.psychology.mvp.views.examples.ExCardsView
import javax.inject.Inject

@InjectViewState
class ExCardsPresenter: MvpPresenter<ExCardsView>() {

    @Inject
    lateinit var experimentsLoader: ExperimentsLoader

    @Inject
    lateinit var fixingLoader: FixingLoader

    init {
        DefaultApplication.examplesComponent.inject(this)
    }

    fun loadDataExperiments() {
        val titles: MutableList<String> = mutableListOf()

        for (item in experimentsLoader.getExperiments()) {
            titles.add(item.title)
        }

        for (item in fixingLoader.getFixings()) {
            titles.add(item.title)
        }

        viewState.showDataExperiments(titles)
    }

    fun loadDataFixing() {
//        val titles: MutableList<String> = mutableListOf()
//
//        for (item in fixingLoader.getFixings()) {
//            titles.add(item.title)
//        }
//
//        viewState.showDataFixing(titles)
    }
}