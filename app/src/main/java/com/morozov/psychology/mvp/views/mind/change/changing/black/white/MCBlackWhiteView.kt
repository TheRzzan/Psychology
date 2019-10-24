package com.morozov.psychology.mvp.views.mind.change.changing.black.white

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface MCBlackWhiteView: MvpView {

    fun showThink(situation: String, newThink: String)

    fun showRecycler(data: List< String>)
}