package com.morozov.psychology.mvp.presenters.diary

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.morozov.psychology.DefaultApplication
import com.morozov.psychology.domain.interfaces.diary.ThinkLoader
import com.morozov.psychology.mvp.models.diary.ThinkModel
import com.morozov.psychology.mvp.views.diary.DiaryView
import com.morozov.psychology.utility.DateConverter
import java.text.SimpleDateFormat
import java.util.*
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

        val todayDate = Date()

        for (item in thinksAll) {
            if (monthFormat.format(item.date) == monthFormat.format(todayDate))
                thinksLastMonth.add(item)
        }

        var i = 0
        while (i < thinksLastMonth.size) {
            val thinkModel = thinksLastMonth[i]

            val dateTmp = dayFormat.format(thinkModel.date)
            elements.add(Pair(
                dayFormat.format(thinkModel.date).toInt(),
                DateConverter.getStringMonthSimple(monthFormat.format(todayDate))
            ))

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

        if (elements[elements.size - 1].first != dayFormat.format(todayDate).toInt()) {
            elements.add(
                Pair(
                    dayFormat.format(todayDate).toInt(),
                    DateConverter.getStringMonthSimple(monthFormat.format(todayDate))
                )
            )

            lastMonthData.add(mutableListOf())
        }
        viewState.showDates(elements)
        selectDay(lastMonthData.size - 1)
    }

    fun selectDay(position: Int) {
        if (position >= lastMonthData.size || lastMonthData.isEmpty())
            return

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