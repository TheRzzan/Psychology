package com.morozov.psychology.mvp.presenters.examples

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.morozov.psychology.mvp.views.examples.ExFixTestsView

@InjectViewState
class ExFixTestsPresenter: MvpPresenter<ExFixTestsView>() {

    fun loadData() {
        viewState.showData(listOf("Ситуация", "Мысль", "Эмоции", "Поведение"))
    }

    fun showResults() {
        viewState.showResults()
    }
}