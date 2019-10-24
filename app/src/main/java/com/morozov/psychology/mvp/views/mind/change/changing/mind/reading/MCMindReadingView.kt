package com.morozov.psychology.mvp.views.mind.change.changing.mind.reading

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface MCMindReadingView: MvpView {

    fun showThink(situation: String, newThink: String)
}