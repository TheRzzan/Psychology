package com.morozov.psychology.ui.activities

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.view.View
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.psychology.R
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.mvp.views.MainView
import com.morozov.psychology.ui.fragments.settings.SettingsFragment
import com.morozov.psychology.ui.fragments.diary.DiaryEditorFragment
import com.morozov.psychology.ui.fragments.diary.DiaryFragment
import com.morozov.psychology.ui.fragments.diary.DiaryThinkViewingFragment
import com.morozov.psychology.ui.fragments.examples.*
import com.morozov.psychology.ui.fragments.mind.change.MindChangeFragment
import com.morozov.psychology.ui.fragments.settings.SettingsStyleFragment
import com.morozov.psychology.ui.fragments.settings.SettingsWallpaperFragment
import com.morozov.psychology.ui.fragments.tests.*
import com.morozov.psychology.utility.AppConstants
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : MvpAppCompatActivity(), MainView {

    @InjectPresenter
    lateinit var mPresenter: MainPresenter

    /*
    * Bottom Navigation
    *
    * */
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_examples -> {
                val exCardsFragment = ExCardsFragment()
                exCardsFragment.mActivityPresenter = mPresenter

                clearBackStack()
                setFragment(exCardsFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_diary -> {
                val diaryFragment = DiaryFragment()
                diaryFragment.mActivityPresenter = mPresenter

                clearBackStack()
                setFragment(diaryFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_tests -> {
                val testsFragment = TestsFragment()

                testsFragment.mActivityPresenter = mPresenter

                clearBackStack()
                setFragment(testsFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_mind_change -> {
                setFragment(MindChangeFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_settings -> {
                val settingsFragment = SettingsFragment()
                settingsFragment.mActivityPresenter = mPresenter
                setFragment(settingsFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mPresenter.showExCards()
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        linearBack.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        when (supportFragmentManager.backStackEntryCount) {
            0 -> super.onBackPressed()
            1 -> {
                showBottomNav()
                hideBackArrow()
                supportFragmentManager.popBackStack()
            }
            else -> {
                val fragment = supportFragmentManager.fragments[supportFragmentManager.fragments.size - 1]
                if (fragment is ExTestsFragment ||
                    fragment is ExFixTestsFragment) {
                    hideBackArrow()
                }

                supportFragmentManager.popBackStack()
            }
        }
    }

    /*
    * Interface controls
    * (MainView impl)
    * */
    override fun showBottomNav() {
        navigation.visibility = View.VISIBLE
    }

    override fun hideBottomNav() {
        navigation.visibility = View.GONE
    }

    override fun showBackArrow() {
        linearBack.visibility = View.VISIBLE
    }

    override fun hideBackArrow() {
        linearBack.visibility = View.GONE
    }

    /*
    * Experiments section controls
    * (MainView impl)
    * */
    override fun showExCards() {
        navigation.selectedItemId = R.id.navigation_examples
    }

    override fun showExDescr(position: Int) {
        val exDescriptionFragment = ExDescriptionFragment()

        val bundle = Bundle()
        bundle.putInt(AppConstants.EXP_POSITION, position)

        exDescriptionFragment.arguments = bundle
        exDescriptionFragment.mActivityPresenter = mPresenter

        setFragment(exDescriptionFragment, true)
    }

    override fun showExFixDescr(position: Int) {
        val exFixDescriptionFragment = ExFixDescriptionFragment()

        val bundle = Bundle()
        bundle.putInt(AppConstants.EXP_POSITION, position)

        exFixDescriptionFragment.arguments = bundle
        exFixDescriptionFragment.mActivityPresenter = mPresenter

        setFragment(exFixDescriptionFragment, true)
    }

    override fun showExTest(position: Int) {
        val exTestsFragment = ExTestsFragment()

        val bundle = Bundle()
        bundle.putInt(AppConstants.EXP_POSITION, position)

        exTestsFragment.arguments = bundle
        exTestsFragment.mActivityPresenter = mPresenter

        setFragment(exTestsFragment, true)
    }

    override fun showExFixTest(position: Int) {
        val exFixTestsFragment = ExFixTestsFragment()

        val bundle = Bundle()
        bundle.putInt(AppConstants.EXP_POSITION, position)

        exFixTestsFragment.arguments = bundle
        exFixTestsFragment.mActivityPresenter = mPresenter

        setFragment(exFixTestsFragment, true)
    }

    override fun showExResults(position: Int) {
        val exResultsFragment = ExResultsFragment()

        val bundle = Bundle()
        bundle.putInt(AppConstants.EXP_POSITION, position)

        exResultsFragment.arguments = bundle
        exResultsFragment.mActivityPresenter = mPresenter

        clearBackStackForResults()
        setFragment(exResultsFragment, true)
    }

    override fun showExFixResults(position: Int) {
        val exfixResultsFragment = ExFixResultsFragment()

        val bundle = Bundle()
        bundle.putInt(AppConstants.EXP_POSITION, position)

        exfixResultsFragment.arguments = bundle
        exfixResultsFragment.mActivityPresenter = mPresenter

        clearBackStackForResults()
        setFragment(exfixResultsFragment, true)
    }

    /*
    * Diary section controls
    * (MainView impl)
    * */
    override fun showDiaryCards() {
        navigation.selectedItemId = R.id.navigation_diary
    }

    override fun showDiaryViewing(date: Date) {
        val diaryThinkViewingFragment = DiaryThinkViewingFragment()

        val bundle = Bundle()
        bundle.putSerializable(AppConstants.DIARY_SELECTED_DAY, date)

        diaryThinkViewingFragment.arguments = bundle
        diaryThinkViewingFragment.mActivityPresenter = mPresenter

        setFragment(diaryThinkViewingFragment, true)
    }

    override fun showDiaryEditor(isNew: Boolean, date: Date) {
        val diaryEditorFragment = DiaryEditorFragment()

        val bundle = Bundle()
        bundle.putBoolean(AppConstants.DIARY_IS_NEW_ITEM, isNew)
        bundle.putSerializable(AppConstants.DIARY_SELECTED_DAY, date)

        diaryEditorFragment.arguments = bundle
        diaryEditorFragment.mActivityPresenter = mPresenter

        setFragment(diaryEditorFragment, true)
    }

    /*
    * Test section controls
    *
    * */
    override fun showTestSection() {
        navigation.selectedItemId = R.id.navigation_tests
    }

    override fun showTestDescr(testName: String) {
        val testsDescriptionFragment = TestsDescriptionFragment()

        val bundle = Bundle()
        bundle.putString(AppConstants.TEST_NAME, testName)

        testsDescriptionFragment.arguments = bundle
        testsDescriptionFragment.mActivityPresenter = mPresenter

        setFragment(testsDescriptionFragment, true)
    }

    override fun showTestQuiz(testName: String) {
        val testsQuizFragment = TestsQuizFragment()

        val bundle = Bundle()
        bundle.putString(AppConstants.TEST_NAME, testName)

        testsQuizFragment.arguments = bundle
        testsQuizFragment.mActivityPresenter = mPresenter

        setFragment(testsQuizFragment, true)
    }

    override fun showTestQuizResults(testName: String) {
        val testsResultsFragment = TestsResultsFragment()

        val bundle = Bundle()
        bundle.putString(AppConstants.TEST_NAME, testName)

        testsResultsFragment.arguments = bundle
        testsResultsFragment.mActivityPresenter = mPresenter

        clearBackStackForResults()
        setFragment(testsResultsFragment, true)
    }

    override fun showTestAbout() {
        val testsAboutFragment = TestsAboutFragment()

        testsAboutFragment.mActivityPresenter = mPresenter

        setFragment(testsAboutFragment, true)
    }

    override fun showTestAllResults() {
        val testsAllResultsFragment = TestsAllResultsFragment()

        testsAllResultsFragment.mActivityPresenter = mPresenter

        setFragment(testsAllResultsFragment, true)
    }

    override fun showTestAllResultsCards(testName: String) {
        val testsAllResultsCardsFragment = TestsAllResultsCardsFragment()

        val bundle = Bundle()
        bundle.putString(AppConstants.TEST_NAME, testName)

        testsAllResultsCardsFragment.arguments = bundle
        testsAllResultsCardsFragment.mActivityPresenter = mPresenter

        setFragment(testsAllResultsCardsFragment, true)
    }

    /*
    * Mind change section controls
    *
    * */
    override fun showMindChangeSection() {
        navigation.selectedItemId = R.id.navigation_mind_change
    }

    /*
    * Settings section controls
    *
    * */
    override fun showSettingsSection() {
        navigation.selectedItemId = R.id.navigation_settings
    }

    override fun showSettingsStylesSection() {
        val settingsStyleFragment = SettingsStyleFragment()
        settingsStyleFragment.mActivityPresenter = mPresenter
        setFragment(settingsStyleFragment, true)
    }

    override fun showSettingsWallpaper() {
        val settingsWallpaperFragment = SettingsWallpaperFragment()
        settingsWallpaperFragment.mActivityPresenter = mPresenter
        setFragment(settingsWallpaperFragment, true)
    }

    /*
    *  Helper methods
    *
    *  */

    private fun setFragment(fragment: Fragment, b: Boolean = false) {
        val transaction = supportFragmentManager
            .beginTransaction()
            .replace(R.id.contentMain, fragment)

        if (b)
            transaction.addToBackStack(null)

        transaction.commit()
    }

    private fun clearBackStack() {
        var i = 0
        while (i < supportFragmentManager.backStackEntryCount){
            i++
            supportFragmentManager.popBackStack()
        }
    }

    private fun clearBackStackForResults() {
        supportFragmentManager.popBackStack()
        supportFragmentManager.popBackStack()
    }
}
