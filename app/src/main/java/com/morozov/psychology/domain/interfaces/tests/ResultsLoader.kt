package com.morozov.psychology.domain.interfaces.tests

import com.morozov.psychology.mvp.models.tests.ResultModel

interface ResultsLoader {

    fun getAllResults(testName: String): List<ResultModel>

    fun getLastResult(testName: String): ResultModel?
}