package com.morozov.psychology.mvp.views.mind.change.commitment

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface MCCommitment_1_View: MvpView {

    fun showThink(situation: String, newThink: String)
}