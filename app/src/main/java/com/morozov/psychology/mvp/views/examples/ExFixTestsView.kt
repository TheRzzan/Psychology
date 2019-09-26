package com.morozov.psychology.mvp.views.examples

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.morozov.psychology.mvp.models.examples.ExFixingResultModel

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface ExFixTestsView: MvpView {

    fun showData(description: String, data: List<String>)

    fun showResults(data: List<ExFixingResultModel>)

    fun setFinishEnabled(boolean: Boolean)

    fun outOfTest()

    fun setButtonText(text: String)

    fun setSegmentProgressCount(count: Int)

    fun increaseSegmentProgress()
}