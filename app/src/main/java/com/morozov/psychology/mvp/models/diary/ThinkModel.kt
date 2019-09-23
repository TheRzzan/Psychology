package com.morozov.psychology.mvp.models.diary

import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

data class ThinkModel(val date: Date, var situation: String, var think: String,
                      var emotion: ArrayList<EmotionModel>, var sensation: String)
    : Serializable, Comparable<ThinkModel> {

    override fun compareTo(other: ThinkModel): Int {
        return date.compareTo(other.date)
    }
}