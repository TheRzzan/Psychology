package com.morozov.psychology.mvp.presenters.diary

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.morozov.psychology.DefaultApplication
import com.morozov.psychology.domain.interfaces.diary.ThinkLoader
import com.morozov.psychology.mvp.models.diary.ThinkModel
import com.morozov.psychology.mvp.views.diary.DiaryView
import java.text.SimpleDateFormat
import javax.inject.Inject

@InjectViewState
class DiaryPresenter: MvpPresenter<DiaryView>() {

    @Inject
    lateinit var thinkLoader: ThinkLoader

    init {
        DefaultApplication.diaryComponent.inject(this)
    }

    var lastMonthData: MutableList<MutableList<ThinkModel>> = mutableListOf()

    fun loadData() {
        val thinksAll = thinkLoader.getThinks()
        val thinksLastMonth: MutableList<ThinkModel> = mutableListOf()
        val elements: MutableList<Pair<Int, String>> = mutableListOf()

        val dayFormat = SimpleDateFormat("dd")
        val monthFormat = SimpleDateFormat("MM")

        for (item in thinksAll) {
            if (monthFormat.format(item.date) != "9")
                thinksLastMonth.add(item)
        }

        var i = 0
        while (i < thinksLastMonth.size) {
            val thinkModel = thinksLastMonth[i]

            val dateTmp = dayFormat.format(thinkModel.date)
            elements.add(Pair(dayFormat.format(thinkModel.date).toInt(), "сентябрь"))

            val listTmp = mutableListOf<ThinkModel>()

            listTmp.add(thinkModel)

            var j = i + 1
            while (j < thinksLastMonth.size) {
                if (dayFormat.format(thinksLastMonth[j].date).equals(dateTmp)) {
                    listTmp.add(thinksLastMonth[j])
                    thinksLastMonth.removeAt(j)
                }
                j++
            }

            lastMonthData.add(listTmp)
            i++
        }

        viewState.showDates(elements)
        selectDay(lastMonthData.size - 1)
    }

    fun selectDay(position: Int) {
        val thinks = lastMonthData[position]
        val elements: MutableList<Pair<String, String>> = mutableListOf()

        for (item in thinks) {
            val timeFormat = SimpleDateFormat("HH:mm")
            val pairThinkItem: Pair<String, String> = Pair(timeFormat.format(item.date), item.situation)
            elements.add(pairThinkItem)
        }

        viewState.showThinkList(elements)
    }
}