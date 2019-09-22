package com.morozov.psychology.mvp.models.diary

import java.io.Serializable
import java.util.*

data class ThinkModel(val date: Date, var situation: String, var think: String,
                      var emotion: String, var sensation: String)
    : Serializable, Comparable<ThinkModel> {

    override fun compareTo(other: ThinkModel): Int {
        return date.compareTo(other.date)
    }
}