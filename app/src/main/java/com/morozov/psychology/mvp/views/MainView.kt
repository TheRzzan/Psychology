package com.morozov.psychology.mvp.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface MainView: MvpView {

    fun showBottomNav()

    fun hideBottomNav()

    // examples
    fun showExCards()

    fun showExDescr()

    fun showExTest()

    fun showExResults()
}