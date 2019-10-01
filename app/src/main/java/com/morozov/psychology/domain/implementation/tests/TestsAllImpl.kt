package com.morozov.psychology.domain.implementation.tests

import com.morozov.psychology.domain.interfaces.tests.DescriptionLoader
import com.morozov.psychology.domain.interfaces.tests.QuestionsLoader
import com.morozov.psychology.domain.interfaces.tests.ResultsLoader
import com.morozov.psychology.mvp.models.tests.QuestionModel
import com.morozov.psychology.mvp.models.tests.ResultModel
import java.util.*

class TestsAllImpl: DescriptionLoader, QuestionsLoader, ResultsLoader {

    override fun getDescription(testName: String): Pair<String, String> {
        return Pair("", "")
    }

    override fun getQuestions(testName: String): List<QuestionModel> {
        return listOf()
    }

    override fun getAllResults(testName: String): List<ResultModel> {
        return listOf()
    }

    override fun getLastResult(testName: String): ResultModel {
        return ResultModel(Date(), listOf())
    }
}