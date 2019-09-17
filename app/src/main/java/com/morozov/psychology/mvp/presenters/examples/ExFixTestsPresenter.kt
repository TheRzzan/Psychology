package com.morozov.psychology.mvp.presenters.examples

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.morozov.psychology.mvp.models.ExFixingResultModel
import com.morozov.psychology.mvp.views.examples.ExFixTestsView

@InjectViewState
class ExFixTestsPresenter: MvpPresenter<ExFixTestsView>() {

    fun loadData() {
        viewState.showData(listOf("Ситуация", "Мысль", "Эмоции", "Поведение"))
    }

    fun showResults() {
        viewState.showResults(listOf(ExFixingResultModel("Ситуация", "Артёму отказали на собеседовании на работе", "Артем получил отказ на собеседовании"),
            ExFixingResultModel("Мысль", "Артём недостоин этой работы", "Я недостоин этой работы"),
            ExFixingResultModel("Эмоции", "Печаль", "Грусть"),
            ExFixingResultModel("Поведение", "Не стал отправлять резюме", "Не стал отправлять резюме на другие вакансии")))
    }
}