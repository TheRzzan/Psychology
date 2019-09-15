package com.morozov.psychology.mvp.presenters.examples

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.morozov.psychology.mvp.views.examples.ExCardsView

@InjectViewState
class ExCardsPresenter: MvpPresenter<ExCardsView>() {

    fun loadData() {
        viewState.showData(listOf("Эксперимент №1", "Эксперимент №2", "Эксперимент №3"
            , "Эксперимент №4", "Эксперимент №5"))
    }
}