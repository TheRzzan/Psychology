package com.morozov.psychology.domain.implementation.diary

import android.content.Context
import com.morozov.psychology.domain.interfaces.diary.ThinkDeleter
import com.morozov.psychology.domain.interfaces.diary.ThinkLoader
import com.morozov.psychology.domain.interfaces.diary.ThinkSaver
import com.morozov.psychology.mvp.models.diary.EmotionModel
import com.morozov.psychology.mvp.models.diary.ThinkModel
import com.morozov.psychology.utility.ThinkDBHelper
import java.text.SimpleDateFormat
import java.util.*

class ThinkLoaderImpl(private val context: Context): ThinkLoader, ThinkSaver, ThinkDeleter {

    companion object {
        var dataList: MutableList<ThinkModel> = mutableListOf()
    }

    init {
        if (dataList.isEmpty()) {
            val thinkDBHelper = ThinkDBHelper(context)

            val count = thinkDBHelper.getCount()
            var i = 0

            while (i < count) {
                thinkDBHelper.getItemAt(i)?.let { dataList.add(it) }
                i++
            }
        }
    }

    override fun getThinks(): List<ThinkModel> = sordThinksByDate(dataList)

    override fun getThinkByDate(date: Date): ThinkModel? {
        for (item in dataList)
            if (item.date.compareTo(date) == 0)
                return item

        return null
    }

    fun sordThinksByDate(data: MutableList<ThinkModel>): MutableList<ThinkModel> {
        data.sort()
        return data
    }

    override fun saveNew(think: ThinkModel) {
        dataList.add(think)
        val thinkDBHelper = ThinkDBHelper(context)
        thinkDBHelper.addThink(think)
    }

    override fun overwriteThink(think: ThinkModel) {
        val smpDtFrm = SimpleDateFormat("dd/MM/yyyy HH:mm")

        for (thinkTmp in dataList) {
            if (smpDtFrm.format(think.date) == smpDtFrm.format(thinkTmp.date)) {

                dataList.remove(thinkTmp)
                dataList.add(think)

                val thinkDBHelper = ThinkDBHelper(context)

                thinkDBHelper.removeItemWithDate(thinkTmp.date)
                thinkDBHelper.addThink(think)

                break
            }
        }
    }

    override fun deleteThink(think: ThinkModel) {
        val smpDtFrm = SimpleDateFormat("dd/MM/yyyy HH:mm")

        for (thinkTmp in dataList) {
            if (smpDtFrm.format(think.date) == smpDtFrm.format(thinkTmp.date)) {
                dataList.remove(thinkTmp)

                val thinkDBHelper = ThinkDBHelper(context)
                thinkDBHelper.removeItemWithDate(thinkTmp.date)

                break
            }
        }
    }
}