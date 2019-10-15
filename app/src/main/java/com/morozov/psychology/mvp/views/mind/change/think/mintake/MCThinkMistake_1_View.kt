package com.morozov.psychology.mvp.views.mind.change.think.mintake

import android.graphics.drawable.Drawable
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface MCThinkMistake_1_View: MvpView {

    fun showThinkMistakes(data: List<Pair<Drawable, Pair<String, String>>>)
}