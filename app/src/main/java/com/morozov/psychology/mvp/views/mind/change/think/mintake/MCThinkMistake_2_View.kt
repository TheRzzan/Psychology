package com.morozov.psychology.mvp.views.mind.change.think.mintake

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.morozov.psychology.mvp.models.diary.ThinkModel

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface MCThinkMistake_2_View: MvpView {

    fun showThink(thinkModel: ThinkModel)
}