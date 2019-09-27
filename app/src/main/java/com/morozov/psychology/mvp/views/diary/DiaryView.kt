package com.morozov.psychology.mvp.views.diary

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.morozov.psychology.mvp.models.diary.ThinkModel
import java.util.*

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface DiaryView: MvpView {

    fun showIsEmptyMessage(b: Boolean)

    fun showDates(elements: List<Date>)

    fun showThinkList(elements: List<ThinkModel>)

    fun showCalendar()
}