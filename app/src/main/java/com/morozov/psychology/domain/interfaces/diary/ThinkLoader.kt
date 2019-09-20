package com.morozov.psychology.domain.interfaces.diary

import com.morozov.psychology.mvp.models.diary.ThinkModel

interface ThinkLoader {

    fun getThinks(): List<ThinkModel>
}