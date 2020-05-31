package com.morozov.psychology.mvp.models.diary

import com.applandeo.materialcalendarview.EventDay
import com.morozov.psychology.R
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

data class ThinkModel(val date: Date, var situation: String, var think: String,
                      var emotion: ArrayList<EmotionModel>, var sensation: String, var isOverwrited: Boolean = false)
    : Serializable, Comparable<ThinkModel> {

    override fun compareTo(other: ThinkModel): Int {
        return date.compareTo(other.date)
    }

    fun toEvent(): EventDay = EventDay(
        Calendar.getInstance().apply {
            timeInMillis = date.time
        },
        R.drawable.circle_accent
    )
}