package com.morozov.psychology.domain.implementation.diary

import com.morozov.psychology.domain.interfaces.diary.ThinkLoader
import com.morozov.psychology.mvp.models.diary.ThinkModel

class ThinkLoaderImpl: ThinkLoader {

    override fun getThinks(): List<ThinkModel> {

        return listOf()
    }
}