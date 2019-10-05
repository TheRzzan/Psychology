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
        val maxAnswers = 7

        val summationPoints = listOf( 2,  6,  12,  17,  18,  23,  24,  29,  30,  35,  37,  40)
        val reversePoints = listOf( 1,  3,  4,  5,  7,  8,  9,  10,  11,  13,  14,  15,  16,  19,  20,  21,  22,  25,  26,  27,  28,  31,  32,  33,  34,  36,  38,  39)

        var score = 0

        var i = 0
        while (i < answers.size) {
            if (summationPoints.contains(i + 1)) {
                score += answers[i] + 1
            } else if (reversePoints.contains(i + 1)) {
                score += reverceInt(answers[i] + 1, maxAnswers)
            }

            i ++
        }

        return getWBResStr(score)
    }

    private fun getWBResStr(score: Int): ResultModel {
        val tmpStr = "Вы набрали $score баллов"

        return when {
            score in 103..138 -> ResultModel(Date(), listOf(Pair(tmpStr, "У вас встречаются ошибки мышления, но их количество не превышает уровень нормы.")))
            score <= 102 -> ResultModel(Date(), listOf(Pair(tmpStr, "Поздравляем, у вас очень мало ошибок мышления.")))
            score >= 139 -> ResultModel(Date(), listOf(Pair(tmpStr, "К сожалению, вы допускаете много ошибок мышления, часто даете неадекватную оценку ситуациям. Обычно это приводить к длительным негативным эмоциям или даже телесным симптомам. Рекомендуем поработать над своими мыслями.")))
            else -> ResultModel(Date(), listOf())
        }
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

    /*
    * Helper functions
    *
    * */
    private fun reverceInt(value: Int, max: Int): Int {
        return max + 1 - value
    }
}