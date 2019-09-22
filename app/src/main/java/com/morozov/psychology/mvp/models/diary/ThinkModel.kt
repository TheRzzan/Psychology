package com.morozov.psychology.mvp.models.diary

import java.io.Serializable
import java.util.*

data class ThinkModel(val date: Date, val situation: String, val think: String,
                      val emotion: String, val sensation: String) : Serializable