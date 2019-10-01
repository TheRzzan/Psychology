package com.morozov.psychology.mvp.views.tests

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface TestsDescriptionView: MvpView {

    fun showDescription(name: String, description: String)
}