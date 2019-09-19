package com.morozov.psychology.mvp.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface MainView: MvpView {

    /*
    * Interface controls
    *
    * */
    fun showBottomNav()

    fun hideBottomNav()

    /*
    * Experiments section controls
    *
    * */
    fun showExCards()

    fun showExDescr(position: Int)

    fun showExFixDescr()

    fun showExTest(position: Int)

    fun showExFixTest()

    fun showExResults(position: Int)
}