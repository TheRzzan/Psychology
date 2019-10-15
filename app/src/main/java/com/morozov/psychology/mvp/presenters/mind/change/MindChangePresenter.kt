package com.morozov.psychology.mvp.presenters.mind.change

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.morozov.psychology.DefaultApplication
import com.morozov.psychology.domain.interfaces.diary.ThinkLoader
import com.morozov.psychology.mvp.models.diary.ThinkModel
import com.morozov.psychology.mvp.views.mind.change.MindChangeView
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@InjectViewState
class MindChangePresenter:MvpPresenter<MindChangeView>() {

    @Inject
    lateinit var thinkLoader: ThinkLoader

    init {
        DefaultApplication.diaryComponent.inject(this)
    }

    lateinit var tmpThinkList: MutableList<ThinkModel>

    fun showThinkList(day: Int) {
        tmpThinkList = loadThinkList(day)

        viewState.showEmptyDate(tmpThinkList.isEmpty())
        viewState.showSelectDate(false)
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
}