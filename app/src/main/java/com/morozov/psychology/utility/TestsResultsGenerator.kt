package com.morozov.psychology.utility

import com.morozov.psychology.mvp.models.tests.ResultModel
import java.util.*

class TestsResultsGenerator {

    fun getResult(testName: String, answers: List<Int>): ResultModel = when (testName) {
            AppConstants.WEISMAN_BACK_TEST -> {
                getWeismanBackRes(testName, answers)
            }
            AppConstants.ELLIS_TEST -> {
                getEllisRes(testName, answers)
            }
            AppConstants.HOSPITAL_SCALE_TEST -> {
                getHospitalScaleRes(testName, answers)
            }
            AppConstants.LAZARUS_QUESTIONNAIRE_TEST -> {
                getLazarusQuestionnaireRes(testName, answers)
            }
            AppConstants.SELF_ATTITUDE_TEST -> {
                getSelfAttitudeRes(testName, answers)
            }
            AppConstants.STYLE_INDEX_TEST -> {
                getStyleIndexRes(testName, answers)
            }
            AppConstants.INTEGRATIVE_TEST -> {
                getIntegrativeRes(testName, answers)
            }
            else -> ResultModel(Date(), listOf(Pair(testName, "Some description ${answers.size}")))
        }

    private fun getWeismanBackRes(testName: String, answers: List<Int>): ResultModel {
        var score = 0

        for (item in answers) {
            score += item + 1
        }

        return ResultModel(Date(), listOf(Pair(testName, "Some description ${answers.size}")))
    }

    private fun getEllisRes(testName: String, answers: List<Int>): ResultModel {
        return ResultModel(Date(), listOf(Pair(testName, "Some description ${answers.size}")))
    }

    private fun getHospitalScaleRes(testName: String, answers: List<Int>): ResultModel {
        return ResultModel(Date(), listOf(Pair(testName, "Some description ${answers.size}")))
    }

    private fun getIntegrativeRes(testName: String, answers: List<Int>): ResultModel {
        return ResultModel(Date(), listOf(Pair(testName, "Some description ${answers.size}")))
    }

    private fun getLazarusQuestionnaireRes(testName: String, answers: List<Int>): ResultModel {
        return ResultModel(Date(), listOf(Pair(testName, "Some description ${answers.size}")))
    }

    private fun getStyleIndexRes(testName: String, answers: List<Int>): ResultModel {
        return ResultModel(Date(), listOf(Pair(testName, "Some description ${answers.size}")))
    }

    private fun getSelfAttitudeRes(testName: String, answers: List<Int>): ResultModel {
        return ResultModel(Date(), listOf(Pair(testName, "Some description ${answers.size}")))
    }
}