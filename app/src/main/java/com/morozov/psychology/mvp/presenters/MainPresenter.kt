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
        viewState.hideBackArrow()
        viewState.showExCards()
    }

    fun showExDescr(position: Int) {
        viewState.hideBottomNav()
        viewState.hideBackArrow()
        viewState.showExDescr(position)
    }

    fun showExFixDescr(position: Int) {
        viewState.hideBottomNav()
        viewState.hideBackArrow()
        viewState.showExFixDescr(position)
    }

    fun showExTest(position: Int) {
        viewState.hideBottomNav()
        viewState.showBackArrow()
        viewState.showExTest(position)
    }

    fun showExFixTest(position: Int) {
        viewState.hideBottomNav()
        viewState.showBackArrow()
        viewState.showExFixTest(position)
    }

    fun showExResults(position: Int) {
        viewState.hideBottomNav()
        viewState.showBackArrow()
        viewState.showExResults(position)
    }

    fun showExFixResults(position: Int) {
        viewState.hideBottomNav()
        viewState.showBackArrow()
        viewState.showExFixResults(position)
    }

    /*
    * Diary section controls
    *
    * */
    fun showDiaryCards() {
        viewState.showBottomNav()
        viewState.hideBackArrow()
        viewState.showDiaryCards()
    }

    fun showDiaryViewing(date: Date) {
        viewState.hideBottomNav()
        viewState.showBackArrow()
        viewState.showDiaryViewing(date)
    }

    fun showDiaryEditor(isNew: Boolean, date: Date) {
        viewState.hideBottomNav()
        viewState.showBackArrow()
        viewState.showDiaryEditor(isNew, date)
    }

    /*
    * Test section controls
    *
    * */
    fun showTestSection() {
        viewState.showBottomNav()
        viewState.hideBackArrow()
        viewState.showTestSection()
    }

    fun showTestDescr() {
        viewState.hideBottomNav()
        viewState.showBackArrow()
        viewState.showTestDescr()
    }

    fun showTestQuiz() {
        viewState.hideBottomNav()
        viewState.showBackArrow()
        viewState.showTestQuiz()
    }

    /*
    * Mind change section controls
    *
    * */
    fun showMindChangeSection() {
        viewState.showBottomNav()
        viewState.hideBackArrow()
        viewState.showMindChangeSection()
    }

    /*
    * Profile section controls
    *
    * */
    fun showProfileSection() {
        viewState.showBottomNav()
        viewState.hideBackArrow()
        viewState.showProfileSection()
    }
}