package com.morozov.psychology.mvp.views.settings

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface SettingsStyleView: MvpView {

    fun showColors(data: List<Pair<String, Int>>)
}