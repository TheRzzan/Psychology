package com.morozov.psychology.mvp.views.mind.change

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.morozov.psychology.mvp.models.diary.ThinkModel

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface MindChangeView: MvpView {

    fun showSelectDate(b: Boolean)

    fun showEmptyDate(b: Boolean)

    fun showThinkList(elements: List<ThinkModel>)

    fun showCalendar()
}