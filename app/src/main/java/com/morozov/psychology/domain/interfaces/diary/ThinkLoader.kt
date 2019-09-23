package com.morozov.psychology.domain.interfaces.diary

import com.morozov.psychology.mvp.models.diary.ThinkModel
import java.util.*

interface ThinkLoader {

    fun getThinks(): List<ThinkModel>

    fun getThinkByDate(date: Date): ThinkModel?
}