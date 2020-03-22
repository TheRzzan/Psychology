package com.morozov.psychology.mvp.presenters.mind.change.changing.labeling

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.morozov.psychology.DefaultApplication
import com.morozov.psychology.domain.interfaces.diary.ThinkLoader
import com.morozov.psychology.domain.interfaces.diary.ThinkSaver
import com.morozov.psychology.mvp.models.diary.EmotionModel
import com.morozov.psychology.mvp.views.mind.change.MindChangeThinkTestView
import com.morozov.psychology.mvp.views.mind.change.changing.labeling.MCLabelingView
import java.util.*
import javax.inject.Inject

@InjectViewState
class MCLabelingPresenter: MvpPresenter<MCLabelingView>() {

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
    }

    fun saveNewThink(think: String, emotion: EmotionModel): Date? {
        val thinkByDate = MindChangeThinkTestView.date?.let { thinkLoader.getThinkByDate(it) }

        return if (thinkByDate != null) {
            thinkByDate.think = think
            thinkByDate.emotion = arrayListOf(emotion)
            thinkByDate.isOverwrited = true

            thinkSaver.overwriteThink(thinkByDate)
            thinkByDate.date
        } else
            null
    }
}