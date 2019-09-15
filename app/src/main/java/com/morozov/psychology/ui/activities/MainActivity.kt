package com.morozov.psychology.ui.activities

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.morozov.psychology.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_examples -> {
                message.setText(R.string.title_examples)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_diary -> {
                message.setText(R.string.title_diary)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_tests -> {
                message.setText(R.string.title_tests)
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
