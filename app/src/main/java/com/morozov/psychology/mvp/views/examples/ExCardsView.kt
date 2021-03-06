package com.morozov.psychology.mvp.views.examples

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface ExCardsView: MvpView {

    fun showDataExperiments(data: List<String>)

    fun showDataFixing(data: List<String>)
}