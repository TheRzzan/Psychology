package com.morozov.psychology.ui.activities

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.psychology.R
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.mvp.views.MainView
import com.morozov.psychology.ui.fragments.examples.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.example_cards_layout.*

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

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.contentMain, exCardsFragment)
            .commit()
    }

    override fun showExDescr() {
        val exDescriptionFragment = ExDescriptionFragment()
        exDescriptionFragment.mActivityPresenter = mPresenter

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.contentMain, exDescriptionFragment)
            .commit()
    }

    override fun showExFixDescr() {
        val exFixDescriptionFragment = ExFixDescriptionFragment()
        exFixDescriptionFragment.mActivityPresenter = mPresenter

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.contentMain, exFixDescriptionFragment)
            .commit()
    }

    override fun showExTest() {
        val exTestsFragment = ExTestsFragment()
        exTestsFragment.mActivityPresenter = mPresenter

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.contentMain, exTestsFragment)
            .commit()
    }

    override fun showExFixTest() {
        val exFixTestsFragment = ExFixTestsFragment()
        exFixTestsFragment.mActivityPresenter = mPresenter

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.contentMain, exFixTestsFragment)
            .commit()
    }

    override fun showExResults() {
        val exResultsFragment = ExResultsFragment()
        exResultsFragment.mActivityPresenter = mPresenter

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.contentMain, exResultsFragment)
            .commit()
    }
}
