package com.morozov.psychology.utility

import com.morozov.psychology.mvp.models.tests.ResultModel
import java.util.*
import kotlin.math.max

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

    /*
    * WeismanBack functions
    *
    * */
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

    /*
    * Ellis functions
    *
    * */
    private fun getEllisRes(testName: String, answers: List<Int>): ResultModel {
        val maxAnswers = 6

        val reversePoints = listOf(1, 4, 13, 17, 20, 22, 25, 26, 28, 34, 38, 42, 46, 49)

        var scoreKat = 0
        var scoreDoljSelf = 0
        var scoreDoljOthers = 0
        var scoreLowFr = 0
        var scoreSelfMax = 0

        var i = 0
        while (i < answers.size) {
            val iK = i
            val iDS = i + 1
            val iDO = i + 2
            val iLF = i + 3
            val iSM = i + 4

            if (reversePoints.contains(iK + 1))
                scoreKat += reverceInt(answers[iK] + 1, maxAnswers)
            else
                scoreKat += answers[iK] + 1

            if (reversePoints.contains(iDS + 1))
                scoreDoljSelf += reverceInt(answers[iDS] + 1, maxAnswers)
            else
                scoreDoljSelf += answers[iDS] + 1

            if (reversePoints.contains(iDO + 1))
                scoreDoljOthers += reverceInt(answers[iDO] + 1, maxAnswers)
            else
                scoreDoljOthers += answers[iDO] + 1

            if (reversePoints.contains(iLF + 1))
                scoreLowFr += reverceInt(answers[iLF] + 1, maxAnswers)
            else
                scoreLowFr += answers[iLF] + 1

            if (reversePoints.contains(iSM + 1))
                scoreSelfMax += reverceInt(answers[iSM] + 1, maxAnswers)
            else
                scoreSelfMax += answers[iSM] + 1

            i += 5
        }

        return getEResStr(scoreKat, scoreDoljSelf, scoreDoljOthers, scoreLowFr, scoreSelfMax)
    }

    private fun getEResStr(scoreKat: Int, scoreDoljSelf: Int, scoreDoljOthers: Int,
                           scoreLowFr: Int, scoreSelfMax: Int): ResultModel {

        val pK1 = "Вы набрали $scoreKat баллов по шкале катастрофизация."
        val pK2: String = when {
            scoreKat <= 30 -> "Вы склоны сильно преувеливать степень негативных последствий ситуаций. Неблагоприятные события кажутся вам ужасными и невыносимыми. Зачастую это приводит к повышению тревоги. Во время работы с собственными мыслями, рекомендуем вам обратить внимание на проработку ошибки мышления «катастрофизация»."
            scoreKat in 31..45 -> "Вы склоны в некоторой степени преувеливать степень негативных последствий ситуаций. Порой неблагоприятные события кажутся вам ужасными и невыносимыми. Это может повышать уровень тревоги. Во время работы с собственными мыслями, рекомендуем вам обратить внимание на проработку ошибки мышления «катастрофизация»."
            scoreKat >= 46 -> "Вы не склоны преувеливать степень негативных последствий ситуаций. Неблагоприятные события кажутся вам вполне переносимыми."
            else -> ""
        }
        val p1 = Pair(pK1, pK2)

        val pDS1 = "Вы набрали $scoreDoljSelf баллов по шкале долженствование в отношении себя."
        val pDS2: String = when {
            scoreDoljSelf <= 30 -> "Вы очень часто предъявляете чрезмерно высокие требования к себе. Это может приводить к высокому чувству вины. Во время работы с собственными мыслями, рекомендуем вам обратить внимание на проработку ошибки мышления «долженствование»."
            scoreDoljSelf in 31..45 -> "Вы порой предъявляете чрезмерно высокие требования к себе. Это может приводить к повышению чувства вины. Во время работы с собственными мыслями, рекомендуем вам обратить внимание на проработку ошибки мышления «долженствование»."
            scoreDoljSelf >= 46 -> "Вы предъявляете достаточно адекватные требования к себе."
            else -> ""
        }
        val p2 = Pair(pDS1, pDS2)

        val pDO1 = "Вы набрали $scoreDoljOthers баллов по шкале долженствование в отношении других."
        val pDO2: String = when {
            scoreDoljOthers <= 30 -> "Вы очень часто предъявляете чрезмерно высокие требования к другим. Часто это приводит к повышению чувства обиды и злости. Во время работы с собственными мыслями, рекомендуем вам обратить внимание на проработку ошибки мышления «долженствование»."
            scoreDoljOthers in 31..45 -> "Вы порой предъявляете чрезмерно высокие требования к другим. Это может приводить к частым обидам и раздражению. Во время работы с собственными мыслями, рекомендуем вам обратить внимание на проработку ошибки мышления «долженствование»."
            scoreDoljOthers >= 46 -> "Вы предъявляете достаточно адекватные требования к другим."
            else -> ""
        }
        val p3 = Pair(pDO1, pDO2)

        val pLF1 = "Вы набрали $scoreLowFr баллов по шкале низкая фрустрационная толерантность."
        val pLF2: String = when {
            scoreLowFr <= 30 -> "У вас очень низкая степень стрессоустойчивости. Высокая вероятность развития стресса. "
            scoreLowFr in 31..45 -> "У вас низкая степень стрессоустойчивости. Средняя вероятность развития стресса."
            scoreLowFr >= 46 -> "Вы достаточно стрессоустойчивы. Низкая вероятность развития стресса."
            else -> ""
        }
        val p4 = Pair(pLF1, pLF2)

        val pSM1 = "Вы набрали $scoreSelfMax баллов по шкале самооценка."
        val pSM2: String = when {
            scoreSelfMax <= 30 -> "Вы часто оцениваете не отдельные черты или поступки людей, а личность в целом. Это касается не только окружающих, но и вас самих. Зачастую это приводит к выраженному снижению самооценки и частому раздражению. Во время работы с собственными мыслями, рекомендуем вам обратить внимание на проработку ошибки мышления «оценивание»."
            scoreSelfMax in 31..45 -> "Вы склонны оценивать не отдельные черты или поступки людей, а личность в целом. Это касается не только окружающих, но и вас самих. Эта привычка может приводить к выраженному снижению самооценки. Во время работы с собственными мыслями, рекомендуем вам обратить внимание на проработку ошибки мышления «оценивание»."
            scoreSelfMax >= 46 -> "Вы умеете не переносить оценку поведения или отдельных черт человека на личность в целом."
            else -> ""
        }
        val p5 = Pair(pSM1, pSM2)

        return ResultModel(Date(), listOf(p1, p2, p3, p4, p5))
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