package com.morozov.psychology.mvp.views.tests

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface TestsAllResultsCardsView: MvpView {

    fun showData(name: String, data: List<Pair<String, List<Pair<String, String>>>>)
}