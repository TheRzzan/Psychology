package com.morozov.psychology.mvp.views.mind.change

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.morozov.psychology.mvp.models.diary.ThinkModel
import java.util.*

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface MindChangeThinkTestView: MvpView {

    companion object {
        var newThink: String? = null
        var date: Date? = null
    }

    fun showProfitability(think: ThinkModel)

    fun showCredibility(newThink: String)
}