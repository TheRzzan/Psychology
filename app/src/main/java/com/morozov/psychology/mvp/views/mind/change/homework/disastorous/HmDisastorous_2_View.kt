package com.morozov.psychology.mvp.views.mind.change.homework.disastorous

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface HmDisastorous_2_View: MvpView {

    fun showRecycler(data: List<Pair<String, String>>)
}