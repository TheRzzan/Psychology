package com.morozov.psychology.mvp.presenters.mind.change.think.mintake

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.morozov.psychology.DefaultApplication
import com.morozov.psychology.domain.interfaces.diary.ThinkLoader
import com.morozov.psychology.mvp.views.mind.change.think.mintake.MCThinkMistake_2_View
import java.util.*
import javax.inject.Inject

@InjectViewState
class MCThinkMistake_2_Presenter: MvpPresenter<MCThinkMistake_2_View>() {

    @Inject
    lateinit var thinkLoader: ThinkLoader

    init {
        DefaultApplication.diaryComponent.inject(this)
    }

    fun loadThink(date: Date) {
        val thinkByDate = thinkLoader.getThinkByDate(date) ?: return
        viewState.showThink(thinkByDate)
    }
}