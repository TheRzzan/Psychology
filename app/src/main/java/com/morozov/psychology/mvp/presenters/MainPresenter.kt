package com.morozov.psychology.mvp.presenters

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.lifecycle.LiveData
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.morozov.psychology.mvp.views.MainView
import java.util.*

@InjectViewState
class MainPresenter:MvpPresenter<MainView>() {

    fun setBackground(drawable: Drawable) {
        viewState.setBackground(drawable)
    }

    fun refreshActivity() {
        viewState.refreshActivity()
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

    fun showMCCommitment_1() {
        viewState.hideBottomNav()
        viewState.showBackArrow()
        viewState.showMCCommitment_1()
    }

    fun showMCCommitment_2() {
        viewState.hideBottomNav()
        viewState.showBackArrow()
        viewState.showMCCommitment_2()
    }

    fun showMCCommitment_3() {
        viewState.hideBottomNav()
        viewState.showBackArrow()
        viewState.showMCCommitment_3()
    }

    fun showMCCommitment_4() {
        viewState.hideBottomNav()
        viewState.showBackArrow()
        viewState.showMCCommitment_4()
    }

    fun showMCPersonalization() {
        viewState.hideBottomNav()
        viewState.showBackArrow()
        viewState.showMCPersonalization()
    }

    fun showMCTunnel() {
        viewState.hideBottomNav()
        viewState.showBackArrow()
        viewState.showMCTunnel()
    }
                                // Homework
    fun showHmMain(date: Date) {
        viewState.hideBottomNav()
        viewState.showBackArrow()
        viewState.showHmMain(date)
    }

    fun showHmDisastorous_1() {
        viewState.hideBottomNav()
        viewState.showBackArrow()
        viewState.showHmDisastorous_1()
    }

    fun showHmDisastorous_2() {
        viewState.hideBottomNav()
        viewState.showBackArrow()
        viewState.showHmDisastorous_2()
    }

    fun showHmDeprecation() {
        viewState.hideBottomNav()
        viewState.showBackArrow()
        viewState.showHmDeprecation()
    }

    fun showHmBlackWhite() {
        viewState.hideBottomNav()
        viewState.showBackArrow()
        viewState.showHmBlackWhite()
    }

    fun showHmEmotional() {
        viewState.hideBottomNav()
        viewState.showBackArrow()
        viewState.showHmEmotional()
    }

    fun showHmMindReading_1() {
        viewState.hideBottomNav()
        viewState.showBackArrow()
        viewState.showHmMindReading_1()
    }

    fun showHmMindReading_2() {
        viewState.hideBottomNav()
        viewState.showBackArrow()
        viewState.showHmMindReading_2()
    }

    fun showHmOvergeneration() {
        viewState.hideBottomNav()
        viewState.showBackArrow()
        viewState.showHmOvergeneration()
    }

    fun showHmMinimalism() {
        viewState.hideBottomNav()
        viewState.showBackArrow()
        viewState.showHmMinimalism()
    }

    fun showHmLabeling() {
        viewState.hideBottomNav()
        viewState.showBackArrow()
        viewState.showHmLabeling()
    }

    fun showHmCommitment_1() {
        viewState.hideBottomNav()
        viewState.showBackArrow()
        viewState.showHmCommitment_1()
    }

    fun showHmCommitment_2() {
        viewState.hideBottomNav()
        viewState.showBackArrow()
        viewState.showHmCommitment_2()
    }

    fun showHmCommitment_3() {
        viewState.hideBottomNav()
        viewState.showBackArrow()
        viewState.showHmCommitment_3()
    }

    fun showHmPersonalization() {
        viewState.hideBottomNav()
        viewState.showBackArrow()
        viewState.showHmPersonalization()
    }

    fun showHmTunnel() {
        viewState.hideBottomNav()
        viewState.showBackArrow()
        viewState.showHmTunnel()
    }

    /*
    * Settings section controls
    *
    * */
    fun showSettingsSection() {
        viewState.hideBottomNav()
        viewState.showBackArrow()
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
        viewState.showBottomNav()
        viewState.hideBackArrow()
        viewState.showSettingsConsult()
    }

    fun showAboutApplication() {
        viewState.hideBottomNav()
        viewState.showBackArrow()
        viewState.showAboutApplication()
    }

    /*
    * Another
    *
    * */
    fun makeBackBlack() {
        viewState.makeBackBlack()
    }

    fun makeBackWhite() {
        viewState.makeBackWhite()
    }
}