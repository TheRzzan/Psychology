package com.morozov.psychology.mvp.views.mind.change.homework.main

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import java.util.*

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface HmMainView: MvpView {

    companion object {
        var date: Date? = null
    }
}