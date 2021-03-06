package com.morozov.psychology.mvp.views.mind.change.changing.tunnel

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface MCTunnelView: MvpView {

    fun showThink(situation: String, newThink: String)
}