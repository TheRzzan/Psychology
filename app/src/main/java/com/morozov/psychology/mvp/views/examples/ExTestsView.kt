package com.morozov.psychology.mvp.views.examples

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface ExTestsView: MvpView {

    fun showTitle(title: String)

    fun showDescription(description: String)

    fun showVariants(variants: List<String>)
}