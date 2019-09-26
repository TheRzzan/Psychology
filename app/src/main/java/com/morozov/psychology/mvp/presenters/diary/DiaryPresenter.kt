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

    companion object {
        var currentDate = -1
    }

    var lastMonthData: MutableList<MutableList<ThinkModel>> = mutableListOf()
    var dateList: MutableList<Date> = mutableListOf()

    fun loadData(dateC: Date? = null) {
        lastMonthData = mutableListOf()
        dateList = mutableListOf()

        val thinksAll = thinkLoader.getThinks()
        val thinksLastMonth: MutableList<ThinkModel> = mutableListOf()
        val elements: MutableList<Pair<Int, String>> = mutableListOf()

        val dayFormat = SimpleDateFormat("dd")
        val monthFormat = SimpleDateFormat("MM")
        val monthYearFormat = SimpleDateFormat("MM/yyyy")
        val dayMtYrFormat = SimpleDateFormat("dd/MM/yyyy")

        val todayDate = Date()

        for (item in thinksAll) {
            if (monthYearFormat.format(item.date) == monthYearFormat.format(todayDate) ||
                (dateC != null && monthYearFormat.format(item.date) == monthYearFormat.format(dateC)))
                thinksLastMonth.add(item)
        }

        if (dateC != null) {
            thinksLastMonth.add(ThinkModel(dateC, "", "", arrayListOf(), ""))
            thinksLastMonth.sort()
        }

        var listTmp = mutableListOf<ThinkModel>()
        for (item in thinksLastMonth) {
            if (dateList.isEmpty() ||
                dayMtYrFormat.format(item.date) != dayMtYrFormat.format(dateList[dateList.size - 1])) {

                dateList.add(item.date)
                elements.add(
                    Pair(
                        dayFormat.format(item.date).toInt(),
                        DateConverter.getStringMonthSimple(monthFormat.format(item.date))
                    )
                )

                listTmp = mutableListOf()
                lastMonthData.add(listTmp)
            }

            if (dateC != null && item.date.compareTo(dateC) == 0)
                currentDate = elements.size - 1
            else
                listTmp.add(item)
        }

        if (dayMtYrFormat.format(todayDate) != dayMtYrFormat.format(dateList[dateList.size - 1])) {
            elements.add(
                Pair(
                    dayFormat.format(todayDate).toInt(),
                    DateConverter.getStringMonthSimple(monthFormat.format(todayDate))
                )
            )

            lastMonthData.add(mutableListOf())
            dateList.add(todayDate)
        }

        if (currentDate < 0 || currentDate >= elements.size)
            currentDate = elements.size - 1

        viewState.showDates(elements)
    }

    fun calendarDateSelected(date: Date) {
        loadData(date)
    }

    fun selectDay(position: Int) {
        currentDate = position

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