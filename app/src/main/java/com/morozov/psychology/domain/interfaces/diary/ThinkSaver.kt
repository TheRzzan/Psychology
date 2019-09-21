package com.morozov.psychology.domain.interfaces.diary

import com.morozov.psychology.mvp.models.diary.ThinkModel

interface ThinkSaver {

    fun saveNew(think: ThinkModel)

    fun overwriteThink(think: ThinkModel)
}