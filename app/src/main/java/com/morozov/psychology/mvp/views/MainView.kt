package com.morozov.psychology.mvp.views

import android.graphics.drawable.Drawable
import android.widget.ImageView
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
    fun showHelloDialog()

    fun startCustomActivity()

    fun showBottomNav()

    fun hideBottomNav()

    fun showBackArrow()

    fun hideBackArrow()

    fun setBackground(drawable: Drawable)

    fun refreshActivity()

    fun setCustomTheme()

    /*
    * Experiments section controls
    *
    * */
    fun showExCards()

    fun showExDescr(image: ImageView?, position: Int)

    fun showExFixDescr(image: ImageView?, position: Int)

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

    fun showDiaryEditor(isNew: Boolean, date: Date, showButtons: Boolean?)

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

    fun showMindChangeThinkTest(date: Date)

    fun showMCThinkMistake_1(date: Date)

    fun showMCThinkMistake_2(date: Date)

    fun showMCDisastorous_1()

    fun showMCDisastorous_2()

    fun showMCDisastorous_3()

    fun showMCDeprecation_1()

    fun showMCDeprecation_2()

    fun showMCDeprecation_3()

    fun showMCBlackWhite()

    fun showMCEmotional()

    fun showMCMindReading()

    fun showMCOvergeneration()

    fun showMCMinimalism()

    fun showMCLabeling()

    fun showMCCommitment_1()

    fun showMCCommitment_2()

    fun showMCCommitment_3()

    fun showMCCommitment_4()

    fun showMCPersonalization()

    fun showMCTunnel()
                        //Homework
    fun showHmMain(date: Date)

    fun showHmDisastorous_1()

    fun showHmDisastorous_2()

    fun showHmDeprecation()

    fun showHmBlackWhite()

    fun showHmEmotional()

    fun showHmMindReading_1()

    fun showHmMindReading_2()

    fun showHmOvergeneration()

    fun showHmMinimalism()

    fun showHmLabeling()

    fun showHmCommitment_1()

    fun showHmCommitment_2()

    fun showHmCommitment_3()

    fun showHmPersonalization()

    fun showHmTunnel()

    /*
    * Profile section controls
    *
    * */
    fun showSettingsSection()

    fun showSettingsStylesSection()

    fun showSettingsWallpaper()

    fun showSettingsConsult()

    /*
    * Another
    *
    * */
    fun makeBackBlack()
    fun makeBackWhite()
}