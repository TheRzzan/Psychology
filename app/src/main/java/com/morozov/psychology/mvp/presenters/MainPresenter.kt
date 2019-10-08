package com.morozov.psychology.mvp.presenters

import android.graphics.drawable.Drawable
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.morozov.psychology.mvp.views.MainView
import java.util.*

@InjectViewState
class MainPresenter:MvpPresenter<MainView>() {

    fun setBackground(drawable: Drawable) {
        viewState.setBackground(drawable)
    }

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

    fun showTestDescr(testName: String) {
        viewState.hideBottomNav()
        viewState.showBackArrow()
        viewState.showTestDescr(testName)
    }

    fun showTestQuiz(testName: String) {
        viewState.hideBottomNav()
        viewState.showBackArrow()
        viewState.showTestQuiz(testName)
    }

    fun showTestQuizResults(testName: String) {
        viewState.hideBottomNav()
        viewState.showBackArrow()
        viewState.showTestQuizResults(testName)
    }

    fun showTestAbout() {
        viewState.hideBottomNav()
        viewState.showBackArrow()
        viewState.showTestAbout()
    }

    fun showTestAllResults() {
        viewState.hideBottomNav()
        viewState.showBackArrow()
        viewState.showTestAllResults()
    }

    fun showTestAllResultsCards(testName: String) {
        viewState.hideBottomNav()
        viewState.showBackArrow()
        viewState.showTestAllResultsCards(testName)
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
    * Settings section controls
    *
    * */
    fun showSettingsSection() {
        viewState.showBottomNav()
        viewState.hideBackArrow()
        viewState.showSettingsSection()
    }

    fun showSettingsStylesSection() {
        viewState.hideBottomNav()
        viewState.showBackArrow()
        viewState.showSettingsStylesSection()
    }

    fun showSettingsWallpaper() {
        viewState.hideBottomNav()
        viewState.hideBackArrow()
        viewState.showSettingsWallpaper()
    }

    fun showSettingsConsult() {
        viewState.hideBottomNav()
        viewState.showBackArrow()
        viewState.showSettingsConsult()
    }
}