package com.morozov.psychology.mvp.presenters.diary

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.morozov.psychology.DefaultApplication
import com.morozov.psychology.domain.interfaces.diary.ThinkDeleter
import com.morozov.psychology.domain.interfaces.diary.ThinkLoader
import com.morozov.psychology.domain.interfaces.diary.ThinkSaver
import com.morozov.psychology.mvp.models.diary.ThinkModel
import com.morozov.psychology.mvp.views.diary.DiaryMainView
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@InjectViewState
class DiaryMainPresenter: MvpPresenter<DiaryMainView>() {

    @Inject
    lateinit var thinkLoader: ThinkLoader

    @Inject
    lateinit var thinkSaver: ThinkSaver

    @Inject
    lateinit var thinkDeleter: ThinkDeleter

    init {
        DefaultApplication.diaryComponent.inject(this)
    }

    companion object {
        var currentDate = -1
    }

    lateinit var tmpThinkList: MutableList<ThinkModel>

    fun showThinkList(position: Int) {
        currentDate = position

        tmpThinkList = loadThinkList(position)
        viewState.showIsEmptyMessage(tmpThinkList.isEmpty())
        viewState.showThinkList(tmpThinkList)
    }

    private fun loadThinkList(position: Int): MutableList<ThinkModel> {
        val calendar = Calendar.getInstance()
        calendar.time = Date(0)
        calendar.add(Calendar.DATE, position)

        val date = calendar.time

        val thinksAll = thinkLoader.getThinks()
        val thinksByDate = mutableListOf<ThinkModel>()
        val dateFormat = SimpleDateFormat("dd/MM/yyyy")

        for (think in thinksAll) {
            if (dateFormat.format(think.date) == dateFormat.format(date))
                thinksByDate.add(think)
        }

        return thinksByDate
    }

    fun deleteThink(think: ThinkModel): ThinkModel? {
        thinkDeleter.deleteThink(think)
        tmpThinkList.remove(think)
        return think
    }

    fun addThink(index: Int, think: ThinkModel) {
        thinkSaver.saveNew(think)
        tmpThinkList.add(index, think)
    }
}