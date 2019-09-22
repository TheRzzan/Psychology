package com.morozov.psychology.mvp.presenters.diary

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.morozov.psychology.DefaultApplication
import com.morozov.psychology.domain.interfaces.diary.ThinkSaver
import com.morozov.psychology.mvp.models.diary.ThinkModel
import com.morozov.psychology.mvp.views.diary.DiaryEditorView
import java.util.*
import javax.inject.Inject

@InjectViewState
class DiaryEditorPresenter: MvpPresenter<DiaryEditorView>() {

    @Inject
    lateinit var thinkSaver: ThinkSaver

    lateinit var dateNew: Date
    lateinit var dateOld: Date

    init {
        DefaultApplication.diaryComponent.inject(this)
    }

    fun initNewThink(date: Date) {
        dateNew = Date()
        viewState.setDate(date)
    }

    fun loadOldThink(date: Date, think: ThinkModel) {
        dateOld = date
        viewState.setDate(date)
        viewState.showThink(think)
    }

    fun saveNewThink(think: ThinkModel) {
        thinkSaver.saveNew(think)
    }

    fun saveOldThink(think: ThinkModel) {
        thinkSaver.overwriteThink(think)
    }
}