package com.morozov.psychology.domain.implementation.diary

import com.morozov.psychology.domain.interfaces.diary.ThinkLoader
import com.morozov.psychology.domain.interfaces.diary.ThinkSaver
import com.morozov.psychology.mvp.models.diary.ThinkModel
import java.text.SimpleDateFormat

class ThinkLoaderImpl: ThinkLoader, ThinkSaver {

    companion object {
        var dataList: MutableList<ThinkModel> = mutableListOf()
    }

    init {
        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm")

        val date1 = simpleDateFormat.parse("15/09/2019 08:00")
        val date2 = simpleDateFormat.parse("16/09/2019 08:10")
        val date3 = simpleDateFormat.parse("17/09/2019 09:15")
        val date4 = simpleDateFormat.parse("18/09/2019 16:50")
        val date5 = simpleDateFormat.parse("18/09/2019 00:00")

        val thModel1 = ThinkModel(date1,
            "Ситуация 1", "Мысль 1", "Эмоция 1", "Озарение 1")

        val thModel2 = ThinkModel(date2,
            "Ситуация 2", "Мысль 2", "Эмоция 2", "Озарение 2")

        val thModel3 = ThinkModel(date3,
            "Ситуация 3", "Мысль 3", "Эмоция 3", "Озарение 3")

        val thModel4 = ThinkModel(date4,
            "Ситуация 4", "Мысль 4", "Эмоция 4", "Озарение 4")

        val thModel5 = ThinkModel(date5,
            "Ситуация 5", "Мысль 5", "Эмоция 5", "Озарение 5")

        dataList.addAll(listOf(thModel1, thModel2, thModel3, thModel4, thModel5))
    }

    override fun getThinks(): List<ThinkModel> = dataList

    override fun saveNew(think: ThinkModel) {
        dataList.add(think)
    }

    override fun overwriteThink(think: ThinkModel) {

    }
}