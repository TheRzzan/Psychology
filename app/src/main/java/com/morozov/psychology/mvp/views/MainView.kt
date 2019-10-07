package com.morozov.psychology.mvp.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.morozov.psychology.mvp.models.diary.ThinkModel
import java.util.*

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface MainView: MvpView {

    /*
    * Interface controls
    *
    * */
    fun showBottomNav()

    fun hideBottomNav()

    fun showBackArrow()

    fun hideBackArrow()

    /*
    * Experiments section controls
    *
    * */
    fun showExCards()

    fun showExDescr(position: Int)

    fun showExFixDescr(position: Int)

    fun showExTest(position: Int)

    fun showExFixTest(position: Int)

    fun showExResults(position: Int)

    fun showExFixResults(position: Int)

    /*
    * Diary section controls
    *
    * */
    fun showDiaryCards()

    fun showDiaryViewing(date: Date)

    fun showDiaryEditor(isNew: Boolean, date: Date)

    /*
    * Test section controls
    *
    * */
    fun showTestSection()

    fun showTestDescr(testName: String)

    fun showTestQuiz(testName: String)

    fun showTestQuizResults(testName: String)

    fun showTestAbout()

    fun showTestAllResults()

    fun showTestAllResultsCards(testName: String)

    /*
    * Mind change section controls
    *
    * */
    fun showMindChangeSection()

    /*
    * Profile section controls
    *
    * */
    fun showSettingsSection()

    fun showSettingsStylesSection()

    fun showSettingsWallpaper()
}