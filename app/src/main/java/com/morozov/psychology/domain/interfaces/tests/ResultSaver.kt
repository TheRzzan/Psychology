package com.morozov.psychology.domain.interfaces.tests

import com.morozov.psychology.mvp.models.tests.ResultModel

interface ResultSaver {

    fun saveResult(testName: String, result: ResultModel)
}