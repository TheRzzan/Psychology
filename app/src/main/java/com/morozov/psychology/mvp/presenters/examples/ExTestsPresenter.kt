package com.morozov.psychology.mvp.presenters.examples

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.morozov.psychology.mvp.views.examples.ExTestsView

@InjectViewState
class ExTestsPresenter: MvpPresenter<ExTestsView>() {

    fun loadData() {
        viewState.showData(listOf("Сергей разозлился. О чем он мог думать, чтобы разозлиться?",
            "Нина расстроилась. О чем она могла думать, чтобы расстроиться?"
            , "Олег удивился. О чем он мог думать, чтобы удивиться?",
            "Максим испугался. О чем он мог подумать, чтобы испугаться?",
            "Марина обрадовалась. О чем она могла думать, чтобы обрадоваться?"))
    }
}