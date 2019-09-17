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

    fun showExFixDescr() {
        viewState.hideBottomNav()
        viewState.showExFixDescr()
    }

    fun showExTest() {
        viewState.hideBottomNav()
        viewState.showExTest()
    }

    fun showExFixTest() {
        viewState.hideBottomNav()
        viewState.showExFixTest()
    }

    fun showExResults() {
        viewState.hideBottomNav()
        viewState.showExResults()
    }
}