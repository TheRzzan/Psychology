package com.morozov.psychology.mvp.presenters

import android.graphics.drawable.Drawable
import android.widget.ImageView
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

    fun showExDescr(image: ImageView?, position: Int) {
        viewState.hideBottomNav()
        viewState.hideBackArrow()
        viewState.showExDescr(image, position)
    }

    fun showExFixDescr(image: ImageView?, position: Int) {
        viewState.hideBottomNav()
        viewState.hideBackArrow()
        viewState.showExFixDescr(image, position)
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

    fun showDiaryEditor(isNew: Boolean, date: Date, showButtons: Boolean?) {
        viewState.hideBottomNav()
        viewState.showBackArrow()
        viewState.showDiaryEditor(isNew, date, showButtons)
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

    fun showMindChangeThinkTest(date: Date) {
        viewState.hideBottomNav()
        viewState.showBackArrow()
        viewState.showMindChangeThinkTest(date)
    }

    fun showMCThinkMistake_1(date: Date) {
        viewState.hideBottomNav()
        viewState.showBackArrow()
        viewState.showMCThinkMistake_1(date)
    }

    fun showMCThinkMistake_2(date: Date) {
        viewState.hideBottomNav()
        viewState.showBackArrow()
        viewState.showMCThinkMistake_2(date)
    }

    fun showMCDisastorous_1() {
        viewState.hideBottomNav()
        viewState.showBackArrow()
        viewState.showMCDisastorous_1()
    }

    fun showMCDisastorous_2() {
        viewState.hideBottomNav()
        viewState.showBackArrow()
        viewState.showMCDisastorous_2()
    }

    fun showMCDisastorous_3() {
        viewState.hideBottomNav()
        viewState.showBackArrow()
        viewState.showMCDisastorous_3()
    }

    fun showMCDeprecation_1(){
        viewState.hideBottomNav()
        viewState.showBackArrow()
        viewState.showMCDeprecation_1()
    }

    fun showMCDeprecation_2(){
        viewState.hideBottomNav()
        viewState.showBackArrow()
        viewState.showMCDeprecation_2()
    }

    fun showMCDeprecation_3(){
        viewState.hideBottomNav()
        viewState.showBackArrow()
        viewState.showMCDeprecation_3()
    }

    fun showMCBlackWhite() {
        viewState.hideBottomNav()
        viewState.showBackArrow()
        viewState.showMCBlackWhite()
    }

    fun showMCEmotional() {
        viewState.hideBottomNav()
        viewState.showBackArrow()
        viewState.showMCEmotional()
    }

    fun showMCMindReading() {
        viewState.hideBottomNav()
        viewState.showBackArrow()
        viewState.showMCMindReading()
    }

    fun showMCOvergeneration() {
        viewState.hideBottomNav()
        viewState.showBackArrow()
        viewState.showMCOvergeneration()
    }

    fun showMCMinimalism() {
        viewState.hideBottomNav()
        viewState.showBackArrow()
        viewState.showMCMinimalism()
    }

    fun showMCLabeling() {
        viewState.hideBottomNav()
        viewState.showBackArrow()
        viewState.showMCLabeling()
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