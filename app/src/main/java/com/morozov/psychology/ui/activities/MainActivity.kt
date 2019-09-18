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
import com.morozov.psychology.ui.fragments.examples.*
import kotlinx.android.synthetic.main.activity_main.*

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
                mPresenter.showExCards()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_diary -> {
                //
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_tests -> {
                //
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_mind_change -> {
                //
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_consultation -> {
                //
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
    }

    override fun onBackPressed() {
        when (supportFragmentManager.backStackEntryCount) {
            0 -> super.onBackPressed()
            1 -> {
                showBottomNav()
                supportFragmentManager.popBackStack()
            }
            else -> {
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

    /*
    * Experiments section controls
    * (MainView impl)
    * */
    override fun showExCards() {
        val exCardsFragment = ExCardsFragment()
        exCardsFragment.mActivityPresenter = mPresenter

        clearBackStack()
        setFragment(exCardsFragment)
    }

    override fun showExDescr() {
        val exDescriptionFragment = ExDescriptionFragment()
        exDescriptionFragment.mActivityPresenter = mPresenter

        setFragment(exDescriptionFragment, true)
    }

    override fun showExFixDescr() {
        val exFixDescriptionFragment = ExFixDescriptionFragment()
        exFixDescriptionFragment.mActivityPresenter = mPresenter

        setFragment(exFixDescriptionFragment, true)
    }

    override fun showExTest() {
        val exTestsFragment = ExTestsFragment()
        exTestsFragment.mActivityPresenter = mPresenter

        setFragment(exTestsFragment, true)
    }

    override fun showExFixTest() {
        val exFixTestsFragment = ExFixTestsFragment()
        exFixTestsFragment.mActivityPresenter = mPresenter

        setFragment(exFixTestsFragment, true)
    }

    override fun showExResults() {
        val exResultsFragment = ExResultsFragment()
        exResultsFragment.mActivityPresenter = mPresenter

        setFragment(exResultsFragment, true)
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
}
