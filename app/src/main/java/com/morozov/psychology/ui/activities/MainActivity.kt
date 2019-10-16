package com.morozov.psychology.ui.activities

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.transition.Fade
import android.view.View
import android.widget.ImageView
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.psychology.R
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.mvp.views.MainView
import com.morozov.psychology.ui.fragments.settings.SettingsFragment
import com.morozov.psychology.ui.fragments.diary.DiaryEditorFragment
import com.morozov.psychology.ui.fragments.diary.DiaryMainFragment
import com.morozov.psychology.ui.fragments.diary.DiaryThinkViewingFragment
import com.morozov.psychology.ui.fragments.examples.*
import com.morozov.psychology.ui.fragments.mind.change.MindChangeFragment
import com.morozov.psychology.ui.fragments.mind.change.MindChangeThinkTestFragment
import com.morozov.psychology.ui.fragments.mind.change.black.white.MCBlackWhiteFragment
import com.morozov.psychology.ui.fragments.mind.change.deprecation.MCDeprecation_1_Fragment
import com.morozov.psychology.ui.fragments.mind.change.deprecation.MCDeprecation_2_Fragment
import com.morozov.psychology.ui.fragments.mind.change.deprecation.MCDeprecation_3_Fragment
import com.morozov.psychology.ui.fragments.mind.change.disastorous.MCDisastorous_1_Fragment
import com.morozov.psychology.ui.fragments.mind.change.disastorous.MCDisastorous_2_Fragment
import com.morozov.psychology.ui.fragments.mind.change.disastorous.MCDisastorous_3_Fragment
import com.morozov.psychology.ui.fragments.mind.change.emotional.MCEmotionalFragment
import com.morozov.psychology.ui.fragments.mind.change.mind.reading.MCMindReadingFragment
import com.morozov.psychology.ui.fragments.mind.change.minimalism.MCMinimalismFragment
import com.morozov.psychology.ui.fragments.mind.change.overgeneration.MCOvergenerationFragment
import com.morozov.psychology.ui.fragments.mind.change.think.mistake.MCThinkMistake_1_Fragment
import com.morozov.psychology.ui.fragments.mind.change.think.mistake.MCThinkMistake_2_Fragment
import com.morozov.psychology.ui.fragments.settings.SettingsConsultFragment
import com.morozov.psychology.ui.fragments.settings.SettingsStyleFragment
import com.morozov.psychology.ui.fragments.settings.SettingsWallpaperFragment
import com.morozov.psychology.ui.fragments.tests.*
import com.morozov.psychology.utility.*
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
                val diaryFragment = DiaryMainFragment()
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

                if (!MySharedPreferences.getBoolPreference(applicationContext, AppConstants.PREF_DIALOG_TESTS)) {
                    val customDialog = CustomDialog()
                    customDialog.setTitle(getString(R.string.dialog_tests_title))
                    customDialog.setDescription(getString(R.string.dialog_tests_description))
                    customDialog.show(supportFragmentManager, CustomDialog::class.simpleName)

                    MySharedPreferences.setPreference(applicationContext, AppConstants.PREF_DIALOG_TESTS, true)
                }

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_mind_change -> {
                val mindChangeFragment = MindChangeFragment()
                mindChangeFragment.mActivityPresenter = mPresenter

                clearBackStack()
                setFragment(mindChangeFragment)
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

        val preferenceWallp = MySharedPreferences.getStrPreference(applicationContext, AppConstants.PREF_WALLPAPER)

        when (preferenceWallp) {
            AppConstants.EMPTY_PREF -> imageMainBack.setImageDrawable(ColorDrawable(resources.getColor(R.color.white)))
            AppConstants.PREF_WALLP_DEF -> imageMainBack.setImageDrawable(ColorDrawable(resources.getColor(R.color.white)))
            AppConstants.PREF_WALLP_1 -> imageMainBack.setImageDrawable(getDrawable(R.drawable.wallpaper_1))
            AppConstants.PREF_WALLP_2 -> imageMainBack.setImageDrawable(getDrawable(R.drawable.wallpaper_2))
            AppConstants.PREF_WALLP_3 -> imageMainBack.setImageDrawable(getDrawable(R.drawable.wallpaper_3))
        }

        mPresenter.showExCards()
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        linearBack.setOnClickListener {
            onBackPressed()
        }

        if (!MySharedPreferences.getBoolPreference(applicationContext, AppConstants.PREF_DIALOG_FIRST_HELLO)) {
            MySharedPreferences.setPreference(applicationContext, AppConstants.PREF_DIALOG_FIRST_HELLO, true)

            val customDialog = CustomDialog()
            customDialog.setTitle(getString(R.string.dialog_first_hello_title))
            customDialog.setDescription(getString(R.string.dialog_first_hello_description))
            customDialog.setOk(getString(R.string.dialog_first_hello_ok))
            customDialog.show(supportFragmentManager, CustomDialog::class.simpleName)
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

    override fun setBackground(drawable: Drawable) {
        imageMainBack.setImageDrawable(drawable)
    }

    /*
        * Experiments section controls
        * (MainView impl)
        * */
    override fun showExCards() {
        navigation.selectedItemId = R.id.navigation_examples
    }

    override fun showExDescr(image: ImageView?, position: Int) {
        val exDescriptionFragment = ExDescriptionFragment()

        val bundle = Bundle()
        bundle.putInt(AppConstants.EXP_POSITION, position)

        exDescriptionFragment.arguments = bundle
        exDescriptionFragment.mActivityPresenter = mPresenter

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            exDescriptionFragment.sharedElementEnterTransition = DetailsTransition()
            exDescriptionFragment.enterTransition = Fade()
            exDescriptionFragment.sharedElementReturnTransition = DetailsTransition()
        }

        val transaction = supportFragmentManager.beginTransaction()

        if (image != null)
            transaction.addSharedElement(image, "experimentImage")

        transaction.replace(R.id.contentMain, exDescriptionFragment)
                   .addToBackStack(null)

        transaction.commit()
    }

    override fun showExFixDescr(image: ImageView?, position: Int) {
        val exFixDescriptionFragment = ExFixDescriptionFragment()

        val bundle = Bundle()
        bundle.putInt(AppConstants.EXP_POSITION, position)

        exFixDescriptionFragment.arguments = bundle
        exFixDescriptionFragment.mActivityPresenter = mPresenter

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            exFixDescriptionFragment.sharedElementEnterTransition = DetailsTransition()
            exFixDescriptionFragment.enterTransition = Fade()
            exFixDescriptionFragment.sharedElementReturnTransition = DetailsTransition()
        }

        val transaction = supportFragmentManager.beginTransaction()

        if (image != null)
            transaction.addSharedElement(image, "fixingImage")

        transaction.replace(R.id.contentMain, exFixDescriptionFragment)
            .addToBackStack(null)

        transaction.commit()
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

    override fun showDiaryEditor(isNew: Boolean, date: Date, showButtons: Boolean?) {
        val diaryEditorFragment = DiaryEditorFragment()

        val bundle = Bundle()
        bundle.putBoolean(AppConstants.DIARY_IS_NEW_ITEM, isNew)
        bundle.putSerializable(AppConstants.DIARY_SELECTED_DAY, date)
        when (showButtons) {
            true -> bundle.putBoolean(AppConstants.DIARY_THINK_EDITOR_SHOW_BUTTONS, true)
            false -> bundle.putBoolean(AppConstants.DIARY_THINK_EDITOR_SHOW_BUTTONS, false)
            null -> bundle.putBoolean(AppConstants.DIARY_THINK_EDITOR_SHOW_BUTTONS, false)
        }

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
        if (!MySharedPreferences.getBoolPreference(applicationContext, AppConstants.PREF_QUIZ_MONTH)) {
            MySharedPreferences.setPreference(applicationContext, AppConstants.PREF_QUIZ_MONTH, true)

            setNotification(AlarmManager.INTERVAL_DAY * 30)
        }
        if (!MySharedPreferences.getBoolPreference(applicationContext, AppConstants.PREF_QUIZ_HALF_YEAR)) {
            MySharedPreferences.setPreference(applicationContext, AppConstants.PREF_QUIZ_HALF_YEAR, true)

            setNotification(AlarmManager.INTERVAL_DAY * 30 * 6)
        }
        if (!MySharedPreferences.getBoolPreference(applicationContext, AppConstants.PREF_QUIZ_YEAR)) {
            MySharedPreferences.setPreference(applicationContext, AppConstants.PREF_QUIZ_YEAR, true)

            setNotification(AlarmManager.INTERVAL_DAY * 30 * 12)
        }

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

        if (!MySharedPreferences.getBoolPreference(applicationContext, AppConstants.PREF_DIALOG_ALL_RESULTS)) {
            val customDialog = CustomDialog()
            customDialog.setTitle(getString(R.string.dialog_tests_all_results_title))
            customDialog.setDescription(getString(R.string.dialog_tests_all_results_description))
            customDialog.show(supportFragmentManager, CustomDialog::class.simpleName)

            MySharedPreferences.setPreference(applicationContext, AppConstants.PREF_DIALOG_ALL_RESULTS, true)
        }
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

    override fun showMindChangeThinkTest(date: Date) {
        val mindChangeThinkTestFragment = MindChangeThinkTestFragment()

        val bundle = Bundle()
        bundle.putSerializable(AppConstants.DIARY_SELECTED_DAY, date)

        mindChangeThinkTestFragment.arguments = bundle
        mindChangeThinkTestFragment.mActivityPresenter = mPresenter

        setFragment(mindChangeThinkTestFragment, true)
    }

    override fun showMCThinkMistake_1(date: Date) {
        val mcThinkMistake_1_Fragment = MCThinkMistake_1_Fragment()

        val bundle = Bundle()
        bundle.putSerializable(AppConstants.DIARY_SELECTED_DAY, date)

        mcThinkMistake_1_Fragment.arguments = bundle
        mcThinkMistake_1_Fragment.mActivityPresenter = mPresenter

        setFragment(mcThinkMistake_1_Fragment, true)
    }

    override fun showMCThinkMistake_2(date: Date) {
        val mcThinkMistake_2_Fragment = MCThinkMistake_2_Fragment()

        val bundle = Bundle()
        bundle.putSerializable(AppConstants.DIARY_SELECTED_DAY, date)

        mcThinkMistake_2_Fragment.arguments = bundle
        mcThinkMistake_2_Fragment.mActivityPresenter = mPresenter

        setFragment(mcThinkMistake_2_Fragment, true)
    }

    override fun showMCDisastorous_1() {
        val fragment = MCDisastorous_1_Fragment()

        fragment.mActivityPresenter = mPresenter

        setFragment(fragment, true)
    }

    override fun showMCDisastorous_2() {
        val fragment = MCDisastorous_2_Fragment()

        fragment.mActivityPresenter = mPresenter

        setFragment(fragment, true)
    }

    override fun showMCDisastorous_3() {
        val fragment = MCDisastorous_3_Fragment()

        fragment.mActivityPresenter = mPresenter

        setFragment(fragment, true)
    }

    override fun showMCDeprecation_1() {
        val fragment = MCDeprecation_1_Fragment()

        fragment.mActivityPresenter = mPresenter

        setFragment(fragment, true)
    }

    override fun showMCDeprecation_2() {
        val fragment = MCDeprecation_2_Fragment()

        fragment.mActivityPresenter = mPresenter

        setFragment(fragment, true)
    }

    override fun showMCDeprecation_3() {
        val fragment = MCDeprecation_3_Fragment()

        fragment.mActivityPresenter = mPresenter

        setFragment(fragment, true)
    }

    override fun showMCBlackWhite() {
        val fragment = MCBlackWhiteFragment()

        fragment.mActivityPresenter = mPresenter

        setFragment(fragment, true)
    }

    override fun showMCEmotional() {
        val fragment = MCEmotionalFragment()

        fragment.mActivityPresenter = mPresenter

        setFragment(fragment, true)
    }

    override fun showMCMindReading() {
        val fragment = MCMindReadingFragment()

        fragment.mActivityPresenter = mPresenter

        setFragment(fragment, true)
    }

    override fun showMCOvergeneration() {
        val fragment = MCOvergenerationFragment()

        fragment.mActivityPresenter = mPresenter

        setFragment(fragment, true)
    }

    override fun showMCMinimalism() {
        val fragment = MCMinimalismFragment()

        fragment.mActivityPresenter = mPresenter

        setFragment(fragment, true)
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

    override fun showSettingsConsult() {
        val settingsConsultFragment = SettingsConsultFragment()
        settingsConsultFragment.mActivityPresenter = mPresenter
        setFragment(settingsConsultFragment, true)
    }

    /*
    *  Helper methods
    *
    *  */
    private fun setNotification(delay: Long) {
        val notificationIntent = Intent(applicationContext, ShowQuizNotification::class.java)
        val contentIntent = PendingIntent.getService(
            applicationContext, 0, notificationIntent,
            PendingIntent.FLAG_CANCEL_CURRENT
        )

        val am = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        am.cancel(contentIntent)
        am.set(
            AlarmManager.RTC_WAKEUP,
            System.currentTimeMillis() + delay, contentIntent
        )
    }

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
