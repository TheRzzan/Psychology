package com.morozov.psychology.mvp.views.diary

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface DiaryView: MvpView {

    fun showIsEmptyMessage(b: Boolean)

    fun showDates(elements: List<Pair<Int, String>>)

    fun showThinkList(elements: List<Pair<String, String>>)
}