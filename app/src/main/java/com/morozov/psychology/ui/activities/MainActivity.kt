package com.morozov.psychology.ui.activities

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.morozov.psychology.R
import com.morozov.psychology.ui.fragments.DiaryFragment
import com.morozov.psychology.ui.fragments.ExamplesFragment
import com.morozov.psychology.ui.fragments.TestsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_examples -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.contentMain, ExamplesFragment())
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_diary -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.contentMain, DiaryFragment())
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_tests -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.contentMain, TestsFragment())
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_mind_change -> {
                message.setText(R.string.title_mind_change)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_consultation -> {
                message.setText(R.string.title_consultation)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
