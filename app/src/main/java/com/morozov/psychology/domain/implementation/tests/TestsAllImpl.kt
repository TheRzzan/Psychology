package com.morozov.psychology.domain.implementation.tests

import com.morozov.psychology.domain.interfaces.tests.DescriptionLoader
import com.morozov.psychology.domain.interfaces.tests.QuestionsLoader
import com.morozov.psychology.domain.interfaces.tests.ResultSaver
import com.morozov.psychology.domain.interfaces.tests.ResultsLoader
import com.morozov.psychology.mvp.models.tests.QuestionModel
import com.morozov.psychology.mvp.models.tests.ResultModel
import com.morozov.psychology.mvp.models.tests.TestModel
import com.morozov.psychology.utility.AppConstants
import java.util.*

class TestsAllImpl: DescriptionLoader, QuestionsLoader, ResultsLoader, ResultSaver {

    companion object {
        var testsList: MutableList<TestModel> = mutableListOf()
    }

    init {
        if (testsList.isEmpty()) {
            //load
        }
    }

    override fun getDescription(testName: String): Pair<String, String> {
        return Pair(getTestByName(testName)!!.name, getTestByName(testName)!!.description)
    }

    override fun getQuestions(testName: String): List<QuestionModel> {
        return getTestByName(testName)!!.questions
    }

    override fun getAllResults(testName: String): List<ResultModel> {
        return getTestByName(testName)!!.results
    }

    override fun getLastResult(testName: String): ResultModel {
        if (getTestByName(testName)!!.results.isEmpty())
            return ResultModel(Date(), listOf())
        else
            return getTestByName(testName)!!.results[getTestByName(testName)!!.results.size - 1]
    }

    override fun saveResult(testName: String, result: ResultModel) {
        getTestByName(testName)!!.results.add(result)
    }

    /*
        * Helper functions
        *
        * */
    private fun getTestByName(testName: String): TestModel? = when (testName) {
        AppConstants.WEISMAN_BACK_TEST -> testsList[0]
        AppConstants.ELLIS_TEST -> testsList[1]
        AppConstants.HOSPITAL_SCALE_TEST -> testsList[2]
        AppConstants.LAZARUS_QUESTIONNAIRE_TEST -> testsList[3]
        AppConstants.SELF_ATTITUDE_TEST -> testsList[4]
        AppConstants.STYLE_INDEX_TEST -> testsList[5]
        AppConstants.INTEGRATIVE_TEST -> testsList[6]
        else -> null
    }
}