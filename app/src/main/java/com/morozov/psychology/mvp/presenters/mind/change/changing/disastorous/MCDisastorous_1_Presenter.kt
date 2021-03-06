package com.morozov.psychology.mvp.presenters.mind.change.changing.disastorous

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.morozov.psychology.DefaultApplication
import com.morozov.psychology.domain.interfaces.diary.ThinkLoader
import com.morozov.psychology.mvp.views.mind.change.MindChangeThinkTestView
import com.morozov.psychology.mvp.views.mind.change.changing.disastorous.MCDisastorous_1_View
import javax.inject.Inject

@InjectViewState
class MCDisastorous_1_Presenter: MvpPresenter<MCDisastorous_1_View>() {

    @Inject
    lateinit var thinkLoader: ThinkLoader

    init {
        DefaultApplication.diaryComponent.inject(this)
    }

    fun loadData() {
        val thinkByDate = MindChangeThinkTestView.date?.let { thinkLoader.getThinkByDate(it) }
        val newThink = MindChangeThinkTestView.newThink
        if (thinkByDate != null && newThink != null)
            viewState.showThink(thinkByDate.situation, newThink)
    }
}