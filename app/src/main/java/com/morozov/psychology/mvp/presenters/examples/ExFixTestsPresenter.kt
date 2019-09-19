package com.morozov.psychology.mvp.presenters.examples

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.morozov.psychology.DefaultApplication
import com.morozov.psychology.domain.interfaces.examples.FixingLoader
import com.morozov.psychology.mvp.models.ExFixingResultModel
import com.morozov.psychology.mvp.views.examples.ExFixTestsView
import javax.inject.Inject

@InjectViewState
class ExFixTestsPresenter: MvpPresenter<ExFixTestsView>() {

    @Inject
    lateinit var fixingLoader: FixingLoader

    init {
        DefaultApplication.examplesComponent.inject(this)
    }

    fun loadData(exercisePos: Int, fixingPos: Int) {
        val pairs = fixingLoader.getFixings()[exercisePos].fixings[fixingPos].pairs

        val texts: MutableList<String> = mutableListOf()

        for (pair in pairs) {
            texts.add(pair.first)
        }

        viewState.showData(texts)
    }

    fun showResults(exercisePos: Int, fixingPos: Int, userAnswers: List<String>) {
        val pairs = fixingLoader.getFixings()[exercisePos].fixings[fixingPos].pairs

        val result: MutableList<ExFixingResultModel> = mutableListOf()
        var i = 0

        for (pair in pairs) {
            result.add(ExFixingResultModel(pair.first, userAnswers[i], pair.second))
            i++
        }

        viewState.showResults(result)
    }
}