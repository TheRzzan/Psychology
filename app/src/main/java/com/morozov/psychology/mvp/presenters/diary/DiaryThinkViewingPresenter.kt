package com.morozov.psychology.mvp.presenters.diary

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.morozov.psychology.DefaultApplication
import com.morozov.psychology.domain.interfaces.diary.ThinkLoader
import com.morozov.psychology.mvp.views.diary.DiaryThinkViewingView
import java.util.*
import javax.inject.Inject

@InjectViewState
class DiaryThinkViewingPresenter: MvpPresenter<DiaryThinkViewingView>() {

    @Inject
    lateinit var thinkLoader: ThinkLoader

    init {
        DefaultApplication.diaryComponent.inject(this)
    }

    fun showThink(date: Date) {
        val thinkByDate = thinkLoader.getThinkByDate(date) ?: return
        viewState.showThink(thinkByDate)
    }
}