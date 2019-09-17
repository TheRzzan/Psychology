package com.morozov.psychology.mvp.views.examples

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.morozov.psychology.mvp.models.ExFixingResultModel

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface ExFixTestsView: MvpView {

    fun showData(data: List<String>)

    fun showResults(data: List<ExFixingResultModel>)
}