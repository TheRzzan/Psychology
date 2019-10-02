package com.morozov.psychology.domain.interfaces.tests

import com.morozov.psychology.mvp.models.tests.ResultModel

interface ResultsLoader {

    fun getAllResults(testName: String): Pair<String, List<ResultModel>>

    fun getLastResult(testName: String): Pair<String, ResultModel?>
}