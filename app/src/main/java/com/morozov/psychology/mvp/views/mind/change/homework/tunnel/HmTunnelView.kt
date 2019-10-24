package com.morozov.psychology.mvp.views.mind.change.homework.tunnel

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface HmTunnelView: MvpView {

    fun showRecycler(data: List<String>)
}