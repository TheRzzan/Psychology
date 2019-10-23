package com.morozov.psychology.mvp.presenters.mind.change.black.white

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.morozov.psychology.DefaultApplication
import com.morozov.psychology.domain.interfaces.diary.ThinkLoader
import com.morozov.psychology.domain.interfaces.diary.ThinkSaver
import com.morozov.psychology.mvp.models.diary.EmotionModel
import com.morozov.psychology.mvp.views.mind.change.MindChangeThinkTestView
import com.morozov.psychology.mvp.views.mind.change.black.white.MCBlackWhiteView
import javax.inject.Inject

@InjectViewState
class MCBlackWhitePresenter: MvpPresenter<MCBlackWhiteView>() {

    @Inject
    lateinit var thinkLoader: ThinkLoader

    @Inject
    lateinit var thinkSaver: ThinkSaver

    init {
        DefaultApplication.diaryComponent.inject(this)
    }

    fun loadData() {
        val thinkByDate = MindChangeThinkTestView.date?.let { thinkLoader.getThinkByDate(it) }
        val newThink = MindChangeThinkTestView.newThink
        if (thinkByDate != null && newThink != null)
            viewState.showThink(thinkByDate.situation, newThink)

        viewState.showRecycler(listOf("0%","10%","20%","30%","40%","50%","60%","70%","80%","90%","100%"))
    }

    fun saveNewThink(think: String, emotion: EmotionModel) {
        val thinkByDate = MindChangeThinkTestView.date?.let { thinkLoader.getThinkByDate(it) }

        if (thinkByDate != null) {
            thinkByDate.think = think
            thinkByDate.emotion = arrayListOf(emotion)
            thinkByDate.isOverwrited = true

            thinkSaver.overwriteThink(thinkByDate)
        }
    }
}