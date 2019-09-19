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

    fun showExDescr(position: Int) {
        viewState.hideBottomNav()
        viewState.showExDescr(position)
    }

    fun showExFixDescr(position: Int) {
        viewState.hideBottomNav()
        viewState.showExFixDescr(position)
    }

    fun showExTest(position: Int) {
        viewState.hideBottomNav()
        viewState.showExTest(position)
    }

    fun showExFixTest(position: Int) {
        viewState.hideBottomNav()
        viewState.showExFixTest(position)
    }

    fun showExResults(position: Int) {
        viewState.hideBottomNav()
        viewState.showExResults(position)
    }
}