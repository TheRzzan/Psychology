package com.morozov.psychology.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.morozov.psychology.mvp.views.MainView

@InjectViewState
class MainPresenter:MvpPresenter<MainView>() {

    /*
    * Experiments section controls
    *
    * */
    fun showExCards() {
        viewState.showBottomNav()
        viewState.showExCards()
    }

    fun showExDescr() {
        viewState.hideBottomNav()
        viewState.showExDescr()
    }

    fun showExTest() {
        viewState.hideBottomNav()
        viewState.showExTest()
    }

    fun showExResults() {
        viewState.hideBottomNav()
        viewState.showExResults()
    }
}