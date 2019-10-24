package com.morozov.psychology.mvp.presenters.mind.change.changing.disastorous

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.morozov.psychology.DefaultApplication
import com.morozov.psychology.domain.interfaces.diary.ThinkLoader
import com.morozov.psychology.domain.interfaces.diary.ThinkSaver
import com.morozov.psychology.mvp.models.diary.EmotionModel
import com.morozov.psychology.mvp.views.mind.change.MindChangeThinkTestView
import com.morozov.psychology.mvp.views.mind.change.changing.disastorous.MCDisastorous_3_View
import javax.inject.Inject

@InjectViewState
class MCDisastorous_3_Presenter: MvpPresenter<MCDisastorous_3_View>() {

    @Inject
    lateinit var thinkLoader: ThinkLoader

    @Inject
    lateinit var thinkSaver: ThinkSaver

    init {
        DefaultApplication.diaryComponent.inject(this)
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