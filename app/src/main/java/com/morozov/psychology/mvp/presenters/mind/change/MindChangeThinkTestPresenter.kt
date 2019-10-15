package com.morozov.psychology.mvp.presenters.mind.change

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.morozov.psychology.DefaultApplication
import com.morozov.psychology.domain.interfaces.diary.ThinkLoader
import com.morozov.psychology.mvp.views.mind.change.MindChangeThinkTestView
import java.util.*
import javax.inject.Inject

@InjectViewState
class MindChangeThinkTestPresenter: MvpPresenter<MindChangeThinkTestView>() {

    @Inject
    lateinit var thinkLoader: ThinkLoader

    init {
        DefaultApplication.diaryComponent.inject(this)
    }

    fun loadThink(date: Date) {
        val thinkByDate = thinkLoader.getThinkByDate(date) ?: return
        viewState.showProfitability(thinkByDate)
    }
}