package com.morozov.psychology.mvp.views.diary

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.morozov.psychology.mvp.models.diary.ThinkModel

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface DiaryMainView: MvpView {

    fun showIsEmptyMessage(b: Boolean)

    fun showThinkList(elements: List<ThinkModel>)

    fun showCalendar()
}