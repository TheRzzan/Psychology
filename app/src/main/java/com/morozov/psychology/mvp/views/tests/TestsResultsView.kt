package com.morozov.psychology.mvp.views.tests

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface TestsResultsView: MvpView {

    fun showResult(data: List<Pair<String, String>>)

    fun showNext(testName: String)

    fun setVisibilityNextButton(b: Boolean)
}