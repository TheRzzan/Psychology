package com.morozov.psychology.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.morozov.psychology.mvp.models.diary.ThinkModel
import com.morozov.psychology.mvp.views.MainView
import java.util.*

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

    fun showExFixResults(position: Int) {
        viewState.hideBottomNav()
        viewState.showExFixResults(position)
    }

    /*
    * Diary section controls
    *
    * */
    fun showDiaryCards() {
        viewState.showBottomNav()
        viewState.showDiaryCards()
    }

    fun showDiaryViewing(date: Date) {
        viewState.hideBottomNav()
        viewState.showDiaryViewing(date)
    }

    fun showDiaryEditor(isNew: Boolean, date: Date) {
        viewState.hideBottomNav()
        viewState.showDiaryEditor(isNew, date)
    }
}