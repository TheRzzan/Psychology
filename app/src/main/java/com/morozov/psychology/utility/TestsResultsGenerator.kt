package com.morozov.psychology.utility

import android.util.Range
import com.morozov.psychology.DefaultApplication
import com.morozov.psychology.domain.interfaces.tests.AboutLoader
import com.morozov.psychology.mvp.models.tests.ResultModel
import com.morozov.psychology.mvp.models.tests.about.enums.SexEnum
import java.util.*
import javax.inject.Inject
import kotlin.math.max

class TestsResultsGenerator {

    @Inject
    lateinit var aboutLoader: AboutLoader

    init {
        DefaultApplication.testsComponent.inject(this)
    }

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

    /*
    * HospitalScale functions
    *
    * */
    private fun getHospitalScaleRes(testName: String, answers: List<Int>): ResultModel {
        val maxAnswers = 2

        var anxietyScore = 0
        var depressionScore = 0

        for ((index, item) in answers.withIndex()) {
            if ( ((index + 1) % 2) == 0)
                depressionScore += item
            else
                anxietyScore += reverceInt(item, maxAnswers)
        }

        return getHSResStr(anxietyScore, depressionScore)
    }

    private fun getHSResStr(anxietyScore: Int, depressionScore: Int): ResultModel {

        val pA1 = "Вы набрали $anxietyScore баллов по шкале тревоги."
        val pA2: String = when {
            anxietyScore <= 7 -> "У вас нет достоверно выраженных симптомов тревоги."
            anxietyScore in 8..10 -> "У вас присутствуют достоверно выраженные симптомы тревоги, но их выраженность не достигает состояния болезни. Рекомендуем обратиться к специалисту для профилактики заболевания и продолжить работу в данном приложении."
            anxietyScore >= 11 -> "У вас присутствуют выраженные симптомы тревоги, их выраженность достигает состояния болезни. Рекомендуем обратиться к психотерапевту на консультацию и продолжить работу в данном приложении."
            else -> ""
        }

        val pD1 = "Вы набрали $depressionScore баллов по шкале депрессии."
        val pD2: String = when {
            depressionScore <= 7 -> "У вас нет достоверно выраженных симптомов депрессии."
            depressionScore in 8..10 -> "У вас присутствуют достоверно выраженные симптомы депрессии, но их выраженность не достигает состояния болезни. Рекомендуем обратиться к специалисту для профилактики заболевания и продолжить работу в данном приложении."
            depressionScore >= 11 -> "У вас присутствуют выраженные симптомы депрессии, их выраженность достигает состояния болезни. Рекомендуем обратиться к психотерапевту на консультацию и продолжить работу в данном приложении. "
            else -> ""
        }

        return ResultModel(Date(), listOf(Pair(pA1, pA2), Pair(pD1, pD2)))
    }

    /*
    * Integrative functions
    *
    * */
    private fun getIntegrativeRes(testName: String, answers: List<Int>): ResultModel {
        val lstED = listOf(
            Pair(1, listOf(0, 25, 49, 74)),
            Pair(2, listOf(0, 24, 49, 73)),
            Pair(4, listOf(0, 27, 53, 80)),
            Pair(6, listOf(0, 24, 49, 73))
        )

        val lstAST = listOf(
            Pair(8,  listOf(0, 30, 61, 91)),
            Pair(13, listOf(0, 41, 81, 122)),
            Pair(14, listOf(0, 29, 58, 87))
        )

        val lstFOB = listOf(
            Pair(7,  listOf(0, 37, 74, 111)),
            Pair(9,  listOf(0, 28, 56, 85)),
            Pair(12, listOf(0, 29, 58, 87))
        )

        val lstOP = listOf(
            Pair(3,  listOf(0, 37, 74, 110)),
            Pair(5,  listOf(0, 32, 65, 98)),
            Pair(15, listOf(0, 31, 61, 92))
        )

        val lstSZ = listOf(
            Pair(10,  listOf(0, 57, 114, 171)),
            Pair(11,  listOf(0, 43, 86, 129))
        )

        var scoreED_LT = 0
        var scoreAST_LT = 0
        var scoreFOB_LT = 0
        var scoreOP_LT = 0
        var scoreSZ_LT = 0

        var scoreED_ST = 0
        var scoreAST_ST = 0
        var scoreFOB_ST = 0
        var scoreOP_ST = 0
        var scoreSZ_ST = 0

        for ((index, item) in answers.withIndex()) {
            for (itTmp in lstED) {
                if ((index + 1) == itTmp.first) {
                    scoreED_LT += itTmp.second[answers[index]]
                } else if ((index + 1) == (itTmp.first + 15)) {
                    scoreED_ST += itTmp.second[answers[index]]
                }
            }
            for (itTmp in lstAST) {
                if ((index + 1) == itTmp.first) {
                    scoreAST_LT += itTmp.second[answers[index]]
                } else if ((index + 1) == (itTmp.first + 15)) {
                    scoreAST_ST += itTmp.second[answers[index]]
                }
            }
            for (itTmp in lstFOB) {
                if ((index + 1) == itTmp.first) {
                    scoreFOB_LT += itTmp.second[answers[index]]
                } else if ((index + 1) == (itTmp.first + 15)) {
                    scoreFOB_ST += itTmp.second[answers[index]]
                }
            }
            for (itTmp in lstOP) {
                if ((index + 1) == itTmp.first) {
                    scoreOP_LT += itTmp.second[answers[index]]
                } else if ((index + 1) == (itTmp.first + 15)) {
                    scoreOP_ST += itTmp.second[answers[index]]
                }
            }
            for (itTmp in lstSZ) {
                if ((index + 1) == itTmp.first) {
                    scoreSZ_LT += itTmp.second[answers[index]]
                } else if ((index + 1) == (itTmp.first + 15)) {
                    scoreSZ_ST += itTmp.second[answers[index]]
                }
            }
        }

        var sScoreED_LT = 0
        var sScoreAST_LT = 0
        var sScoreFOB_LT = 0
        var sScoreOP_LT = 0
        var sScoreSZ_LT = 0

        var sScoreED_ST = 0
        var sScoreAST_ST = 0
        var sScoreFOB_ST = 0
        var sScoreOP_ST = 0
        var sScoreSZ_ST = 0

        val aboutModel = aboutLoader.getAboutModel()

        val mListEd = listOf(
            Range(0, 34), Range(35, 48), Range(49, 62),
            Range(63, 76), Range(77, 100), Range(101, 137),
            Range(138, 173), Range(174, 209), Range(210, 400)
        )
        val mListAST = listOf(
            Range(0, 26), Range(27, 36), Range(37, 47),
            Range(48, 57), Range(58, 82), Range(83, 122),
            Range(123, 161), Range(162, 201), Range(202, 400)
        )
        val mListFOB = listOf(
            Range(0, 13), Range(14, 19), Range(20, 24),
            Range(25, 29), Range(30, 54), Range(55, 99),
            Range(100, 144), Range(145, 188), Range(189, 400)
        )
        val mListOP = listOf(
            Range(0, 44), Range(45, 62), Range(63, 80),
            Range(81, 97), Range(98, 122), Range(123, 155),
            Range(156, 187), Range(188, 219), Range(220, 400)
        )
        val mListSZ = listOf(
            Range(0, 50), Range(51, 70), Range(71, 90),
            Range(91, 110), Range(111, 135), Range(136, 165),
            Range(167, 195), Range(196, 225), Range(226, 400)
        )

        ///////////

        val wListEd = listOf(
            Range(0, 42), Range(43, 58), Range(59, 75),
            Range(76, 92), Range(93, 117), Range(118, 150),
            Range(151, 183), Range(184, 217), Range(218, 400)
        )
        val wListAST = listOf(
            Range(0, 31), Range(32, 44), Range(45, 57),
            Range(58, 70), Range(71, 95), Range(96, 132),
            Range(133, 169), Range(170, 206), Range(207, 400)
        )
        val wListFOB = listOf(
            Range(0, 16), Range(17, 23), Range(24, 29),
            Range(30, 36), Range(37, 61), Range(62, 104),
            Range(105, 148), Range(149, 191), Range(192, 400)
        )
        val wListOP = listOf(
            Range(0, 53), Range(54, 75), Range(76, 97),
            Range(98, 118), Range(119, 143), Range(144, 172),
            Range(173, 200), Range(201, 228), Range(229, 400)
        )
        val wListSZ = listOf(
            Range(0, 60), Range(61, 85), Range(86, 109),
            Range(110, 134), Range(135, 159), Range(160, 184),
            Range(185, 210), Range(211, 235), Range(236, 400)
        )

        if (aboutModel != null) {
            when {
                aboutModel.sex == SexEnum.MAN -> {
                    sScoreED_LT = tmpIRes_1(mListEd, scoreED_LT)
                    sScoreAST_LT = tmpIRes_1(mListAST, scoreAST_LT)
                    sScoreFOB_LT = tmpIRes_1(mListFOB, scoreFOB_LT)
                    sScoreOP_LT = tmpIRes_1(mListOP, scoreOP_LT)
                    sScoreSZ_LT = tmpIRes_1(mListSZ, scoreSZ_LT)

                    sScoreED_ST = tmpIRes_1(mListEd, scoreED_LT)
                    sScoreAST_ST = tmpIRes_1(mListAST, scoreAST_ST)
                    sScoreFOB_ST = tmpIRes_1(mListFOB, scoreFOB_ST)
                    sScoreOP_ST = tmpIRes_1(mListOP, scoreOP_ST)
                    sScoreSZ_ST = tmpIRes_1(mListSZ, scoreSZ_ST)
                }
                aboutModel.sex == SexEnum.WOMAN -> {
                    sScoreED_LT = tmpIRes_1(wListEd, scoreED_LT)
                    sScoreAST_LT = tmpIRes_1(wListAST, scoreAST_LT)
                    sScoreFOB_LT = tmpIRes_1(wListFOB, scoreFOB_LT)
                    sScoreOP_LT = tmpIRes_1(wListOP, scoreOP_LT)
                    sScoreSZ_LT = tmpIRes_1(wListSZ, scoreSZ_LT)

                    sScoreED_ST = tmpIRes_1(wListEd, scoreED_LT)
                    sScoreAST_ST = tmpIRes_1(wListAST, scoreAST_ST)
                    sScoreFOB_ST = tmpIRes_1(wListFOB, scoreFOB_ST)
                    sScoreOP_ST = tmpIRes_1(wListOP, scoreOP_ST)
                    sScoreSZ_ST = tmpIRes_1(wListSZ, scoreSZ_ST)
                }
            }
        }

        return getIResStr(sScoreED_LT, sScoreAST_LT, sScoreFOB_LT, sScoreOP_LT, sScoreSZ_LT,
            sScoreED_ST, sScoreAST_ST, sScoreFOB_ST, sScoreOP_ST, sScoreSZ_ST)
    }

    private fun tmpIRes_1(list: List<Range<Int>>, value: Int): Int {
        for ((index, item) in list.withIndex()) {
            if (item.contains(value)) {
                return index
            }
        }

        return 0
    }

    private fun getIResStr(scoreED_LT: Int, scoreAST_LT: Int, scoreFOB_LT: Int, scoreOP_LT: Int, scoreSZ_LT: Int,
                           scoreED_ST: Int, scoreAST_ST: Int, scoreFOB_ST: Int, scoreOP_ST: Int, scoreSZ_ST: Int): ResultModel {
        var str1 = ""
        var str2 = ""

        when (scoreED_LT) {
            in 0..3 -> str1 += "Тревога не доставляет вам эмоционального дискомфорта."
            in 4..6 -> str1 += "Тревога доставляет вам умеренный эмоциональны дискомфорт."
            in 7..9 -> str1 += "Тревога влияет на ваше настроение, снижая его. В результате возникает неудовлетворенность жизненной ситуацией, эмоциональной напряженностью, периодическое беспокойство."
        }

        when (scoreAST_LT) {
            in 0..3 -> str1 += "Тревога не сопровождается усталостью и быстрой утомляемостью."
            in 4..6 -> str1 += "Тревога периодически приводит к возникновению усталости, но вы достаточно хорошо восстанавливаете свои силы."
            in 7..9 -> str1 += "Тревога влияет на ваше самочувствие. В результате возникает усталость, расстройства сна, вялость и пассивность, быстрая утомляемость."
        }

        when (scoreFOB_LT) {
            in 0..3 -> str1 += "У вас низкий уровень тревоги и страхов."
            in 4..6 -> str1 += "Уровень вашей тревоги и страхов в пределах нормы."
            in 7..9 -> str1 += "Ваша тревога сопровождается ощущениями непонятной угрозы, неуверенности в себе, собственной бесполезности. Вы не всегда можете сформулировать источник своих тревог, как будто страх это хроническое состояние, периодически возрастающее в зависимости от внутреннего состояния или обострения внешней ситуации."
        }

        when (scoreOP_LT) {
            in 0..3 -> str1 += "Представления о будущем не вызывают у вас повышения тревоги."
            in 4..6 -> str1 += "Вы периодически испытываете тревогу о будущем, но умеете достаточно реалистично оценить степень угрозы."
            in 7..9 -> str1 += "Ваши страхи направлены не только на текущее положение дел, но и на перспективу. Вы озабочены будущим, что еще больше усиливает тревогу и эмоциональную чувствительность. "
        }

        when (scoreSZ_LT) {
            in 0..3 -> str1 += "Взаимодействие с окружающими людьми не вызывает у вас тревогу."
            in 4..6 -> str1 += "Тревоги, связанные с социальными контактами находятся у вас на уровне нормы."
            in 7..9 -> str1 += "Основные проявления вашей тревожности находятся в сфере социальных контактов. Окружающие люди могут доставлять вам напряжение и усиливать неуверенность в себе."
        }

        //////////

        when (scoreED_ST) {
            in 0..3 -> str2 += "Тревога не доставляет вам эмоционального дискомфорта."
            in 4..6 -> str2 += "Тревога доставляет вам умеренный эмоциональны дискомфорт."
            in 7..9 -> str2 += "Тревога влияет на ваше настроение, снижая его. В результате возникает неудовлетворенность жизненной ситуацией, эмоциональной напряженностью, периодическое беспокойство."
        }

        when (scoreAST_ST) {
            in 0..3 -> str2 += "Тревога не сопровождается усталостью и быстрой утомляемостью."
            in 4..6 -> str2 += "Тревога периодически приводит к возникновению усталости, но вы достаточно хорошо восстанавливаете свои силы."
            in 7..9 -> str2 += "Тревога влияет на ваше самочувствие. В результате возникает усталость, расстройства сна, вялость и пассивность, быстрая утомляемость."
        }

        when (scoreFOB_ST) {
            in 0..3 -> str2 += "У вас низкий уровень тревоги и страхов."
            in 4..6 -> str2 += "Уровень вашей тревоги и страхов в пределах нормы."
            in 7..9 -> str2 += "Ваша тревога сопровождается ощущениями непонятной угрозы, неуверенности в себе, собственной бесполезности. Вы не всегда можете сформулировать источник своих тревог, как будто страх это хроническое состояние, периодически возрастающее в зависимости от внутреннего состояния или обострения внешней ситуации."
        }

        when (scoreOP_ST) {
            in 0..3 -> str2 += "Представления о будущем не вызывают у вас повышения тревоги."
            in 4..6 -> str2 += "Вы периодически испытываете тревогу о будущем, но умеете достаточно реалистично оценить степень угрозы."
            in 7..9 -> str2 += "Ваши страхи направлены не только на текущее положение дел, но и на перспективу. Вы озабочены будущим, что еще больше усиливает тревогу и эмоциональную чувствительность. "
        }

        when (scoreSZ_ST) {
            in 0..3 -> str2 += "Взаимодействие с окружающими людьми не вызывает у вас тревогу."
            in 4..6 -> str2 += "Тревоги, связанные с социальными контактами находятся у вас на уровне нормы."
            in 7..9 -> str2 += "Основные проявления вашей тревожности находятся в сфере социальных контактов. Окружающие люди могут доставлять вам напряжение и усиливать неуверенность в себе."
        }

        return ResultModel(Date(), listOf(
            Pair("Личностная тревога", str1),
            Pair("Ситуативная тревога", str2)
        ))
    }

    /*
    * LazarusQuestionnaire functions
    *
    * */
    private fun getLazarusQuestionnaireRes(testName: String, answers: List<Int>): ResultModel {
        return ResultModel(Date(), listOf(Pair(testName, "Some description ${answers.size}")))
    }

    private fun getLKonfTable(): List<List<Int>> {
        val l1 = listOf(22,26,29,32,35,38,41,45,48,51,54,57,60,63,67,70,73,76,79)
        val l2 = listOf(22,25,28,32,35,38,42,45,48,51,55,58,61,64,68,71,74,77,81)
        val l3 = listOf(15,19,23,27,31,36,40,44,48,52,56,60,64,68,72,76,80,84,49)
        val l4 = listOf(14,18,22,26,30,34,38,46,46,50,53,57,61,65,69,73,77,81,85)
        //
        val l5 = listOf(16,20,24,28,32,36,39,43,47,51,55,59,63,67,71,75,78,82,86)
        val l6 = listOf(22,26,29,32,35,39,42,45,48,52,55,58,61,64,68,71,74,77,81)
        val l7 = listOf(17,21,24,28,32,35,39,43,46,50,54,57,61,65,68,72,76,79,83)
        val l8 = listOf(19,23,26,29,33,36,40,43,46,50,53,57,60,64,67,70,74,77,81)

        return listOf(l1,l2,l3,l4,l5,l6,l7,l8)
    }

    private fun getLDisfTable(): List<List<Int>> {
        val l1 = listOf(24,27,30,33,37,40,43,46,49,52,56,59,62,65,68,71,75,78,81)
        val l2 = listOf(22,26,29,32,35,38,41,44,48,51,54,57,60,63,67,70,73,76,79)
        val l3 = listOf(14,18,21,25,29,33,37,41,45,49,53,56,60,64,68,72,76,80,84)
        val l4 = listOf(21,24,27,31,34,38,41,44,48,51,54,58,61,64,68,71,74,78,81)
        //
        val l5 = listOf(21,24,28,31,34,38,41,45,48,51,55,58,61,65,68,72,75,78,82)
        val l6 = listOf(24,27,34,33,36,39,42,45,48,52,55,58,61,64,67,70,73,76,79)
        val l7 = listOf(19,22,26,29,32,36,39,43,46,50,53,57,60,64,67,71,74,78,81)
        val l8 = listOf(19,23,26,29,32,36,39,42,45,49,52,55,59,62,65,68,72,75,78)

        return listOf(l1,l2,l3,l4,l5,l6,l7,l8)
    }

    /*
    * StyleIndex functions
    *
    * */
    private fun getStyleIndexRes(testName: String, answers: List<Int>): ResultModel {
        var rawOtr = 0
        var rawPod = 0
        var rawReg = 0
        var rawKom = 0
        var rawPro = 0
        var rawZam = 0
        var rawInt = 0
        var rawRea = 0

        val numsOtr = listOf(1, 16, 22, 28, 34, 42, 51, 61, 68, 77, 82, 90, 94)
        val numsPod = listOf(6, 11, 19, 25, 35, 43, 49, 59, 66, 75, 85, 89)
        val numsReg = listOf(2, 14, 18, 26, 33, 48, 50, 58, 69, 78, 86, 88, 93, 95)
        val numsKom = listOf(3, 10, 24, 29, 37, 45, 52, 64, 65, 74)
        val numsPro = listOf(7, 9, 23, 27, 38, 41, 55, 63, 71, 73, 84, 92, 96)
        val numsZam = listOf(8, 15, 20, 31, 40, 47, 54, 60, 67, 76, 83, 91, 97)
        val numsInt = listOf(4, 13, 17, 30, 36, 44, 56, 62, 70, 80, 81, 87)
        val numsRea = listOf(5, 12, 21, 32, 39, 46, 53, 57, 72, 79)

        for ((index, item) in answers.withIndex()) {
            when {
                numsOtr.contains(index + 1) ->
                    rawOtr += reverceInt(item, 0)
                numsPod.contains(index + 1) ->
                    rawPod += reverceInt(item, 0)
                numsReg.contains(index + 1) ->
                    rawReg += reverceInt(item, 0)
                numsKom.contains(index + 1) ->
                    rawKom += reverceInt(item, 0)
                numsPro.contains(index + 1) ->
                    rawPro += reverceInt(item, 0)
                numsZam.contains(index + 1) ->
                    rawZam += reverceInt(item, 0)
                numsInt.contains(index + 1) ->
                    rawInt += reverceInt(item, 0)
                numsRea.contains(index + 1) ->
                    rawRea += reverceInt(item, 0)
            }
        }

        val resReadyOtr = listOf(3, 13, 27, 39, 50, 61, 79, 81, 90, 97, 98, 99)
        val resReadyPod = listOf(2, 8, 25, 42, 63, 76, 87, 92, 97, 98, 99)
        val resReadyReg = listOf(2, 6, 19, 35, 53, 70, 80, 85, 88, 95, 97, 99)
        val resReadyKom = listOf(5, 20, 37, 63, 78, 88, 95, 97, 99)
        val resReadyPro = listOf(1, 5 ,6 ,7 ,12, 20, 27, 36, 46, 64, 72, 90, 96, 99)
        val resReadyZam = listOf(6, 23, 37, 48, 65, 77, 86, 93, 97, 98, 99)
        val resReadyInt = listOf(0, 3, 6, 17, 28, 42, 59, 76, 87, 92, 97, 99)
        val resReadyRea = listOf(7, 19, 39, 61, 76, 91, 97, 98, 99)

        val readyOtr = if (resReadyOtr.size <= rawOtr) { 100 } else { resReadyOtr[rawOtr] }
        val readyPod = if (resReadyPod.size <= rawPod) { 100 } else { resReadyPod[rawPod] }
        val readyReg = if (resReadyReg.size <= rawReg) { 100 } else { resReadyReg[rawReg] }
        val readyKom = if (resReadyKom.size <= rawKom) { 100 } else { resReadyKom[rawKom] }
        val readyPro = if (resReadyPro.size <= rawPro) { 100 } else { resReadyPro[rawPro] }
        val readyZam = if (resReadyZam.size <= rawZam) { 100 } else { resReadyZam[rawZam] }
        val readyInt = if (resReadyInt.size <= rawInt) { 100 } else { resReadyInt[rawInt] }
        val readyRea = if (resReadyRea.size <= rawRea) { 100 } else { resReadyRea[rawRea] }

        return getSIResStr(readyOtr, readyPod, readyReg,
                readyKom, readyPro, readyZam,
                readyInt, readyRea)
    }

    private fun getSIResStr(readyOtr: Int, readyPod: Int, readyReg: Int,
                            readyKom: Int, readyPro: Int, readyZam: Int,
                            readyInt: Int, readyRea: Int): ResultModel {
        val mutableListOf = mutableListOf<Pair<String, String>>()

        when {
            readyOtr > 50 -> mutableListOf.add(Pair("Шкала отрицание", "Ваша психика стремится защитить вас, отрицая обстоятельства, вызывающие тревогу.  Действие этого механизма проявляется в отрицании тех аспектов внешней реальности, которые, будучи очевидными для окружающих, тем не менее не принимаются, не признаются вами. Иными словами, информация, которая тревожит и может привести к конфликту, не воспринимается. Имеется в виду конфликт, возникающий при проявлении мотивов, противоречащих основным установкам личности или информация, которая угрожает самосохранению, самоуважению, или социальному престижу."))
        }
        when {
            readyPod > 50 -> mutableListOf.add(Pair("Шкала вытеснение", "Ваша психика защищает вас, вытесняя из памяти все неприемлемые импульсы: желания, мысли, чувства, вызывающие тревогу. Подавленные импульсы становятся бессознательными, не находят разрешения в поведении, тем не менее, сохраняют свои эмоциональные переживания и телесные симптомы. Например, вы можете порой  ощущать немотивированную тревогу."))
        }
        when {
            readyReg > 50 -> mutableListOf.add(Pair("Шкала регрессия", "Ваша психика защищает вас от стрессов заменой решений субъективно более сложных задач на относительно более простые и доступные в сложившихся ситуациях. Использование более простых и привычных поведенческих стереотипов существенно обедняет общий (потенциально возможный) арсенал преобладания конфликтных ситуаций. Возникает ощущение, что вы не можете с этим справиться, не знаете как это сделать."))
        }
        when {
            readyKom > 50 -> mutableListOf.add(Pair("Шкала компенсация", "Ваша психика помогает вам найти подходящую замену реальному или воображаемому недостатку, дефекта нестерпимого чувства другим качеством. Чаще всего это происходит с помощью фантазирования или присвоения себе свойств, достоинств, ценностей, поведенческих характеристик другой личности. При этом заимствованные ценности, установки или мысли принимаются без анализа и переструктурирования и поэтому, не становятся частью самой личности.\n" +
                    "Другим проявлением компенсаторных защитных механизмов может быть ситуация преодоления тревожащих обстоятельств сверхудовлетворением своих потребностей в других сферах, например, физически слабый или робкий человек, неспособный ответить на угрозу расправы, находит удовлетворение за счет унижения обидчика с помощью изощренного ума или хитрости. Возможно, вы мечтатель, ищущий идеалы в различных сферах жизнедеятельности."))
        }
        when {
            readyPro > 50 -> mutableListOf.add(Pair("Шкала проекция", "Ваша психика защищает вас от тревоги посредством приписывания окружающим своих неосознаваемых и неприемлемых чувств и мыслей. Например, агрессивность нередко приписывается окружающим, чтобы оправдать свою собственную агрессивность или недоброжелательность, которая проявляется как бы в защитных целях."))
        }
        when {
            readyZam > 50 -> mutableListOf.add(Pair("Шкала замещение", "Ваша психика позволяет вам справляться с подавленными эмоциями, перенося их на других людей. Например, злость на одного человека может быть выражена другому, представляющему меньшую опасность  или более доступному.\n" +
                    "Вы можете совершать неожиданные, подчас бессмысленные действия, которые разрешают внутреннее напряжение."))
        }
        when {
            readyInt > 50 -> mutableListOf.add(Pair("Шкала интеллектуализация", "Ваша психика защищает вас от тревоги чрезмерным умственным способом преодоления конфликтной. Вы пресекаете переживания, вызванные неприятной или субъективно неприемлемой ситуацией при помощи логических установок и манипуляций даже при наличии убедительных доказательств в пользу противоположного. \n" +
                    "Вы способны создавать логические (псевдоразумные), но благовидные обоснования своего или чужого поведения, действий или переживаний, вызванных причинами, которые не можете признать из-за угрозы потери самоуважения. При этом способе защиты нередко наблюдаются очевидные попытки снизить ценность недоступного опыта."))
        }
        when {
            readyRea > 50 -> mutableListOf.add(Pair("Шкала реактивные образования", "Ваша психика предотвращает выражение неприятных или неприемлемых для нее мыслей, чувств или поступков путем преувеличенного развития противоположных стремлений. Иными словами, происходит как бы трансформация внутренних импульсов в субъективно понимаемую их противоположность. Одно из самых известных проявлений этого защитного механизма – бить, чтобы выразить свою любовь."))
        }

        return  ResultModel(Date(), mutableListOf)
    }

    /*
    * SelfAttitude functions
    *
    * */
    private fun getSelfAttitudeRes(testName: String, answers: List<Int>): ResultModel {
        var sc1 = 0
        var sc2 = 0
        var sc3 = 0
        var sc4 = 0
        var sc5 = 0
        var sc6 = 0
        var sc7 = 0
        var sc8 = 0
        var sc9 = 0

        val lsTr1 = listOf(1,3,9,48,53,56,65)
        val lsTr2 = listOf(7,24,30,35,36,51,52,58,61,73,82)
        val lsTr3 = listOf(43,44,45,74,76,84,90,105,106,108,110)
        val lsTr4 = listOf(2,5,29,41,42,50,102)
        val lsTr5 = listOf(8,16,39,54,57,68,70,75,100)
        val lsTr6 = listOf(10,12,17,28,40,49,63,72,77,79,88,97)
        val lsTr7 = listOf(6,32,33,55,89,93,95,101,104)
        val lsTr8 = listOf(4,11,22,23,27,38,47,59,64,67,69,81,91,94,99)
        val lsTr9 = listOf(14,19,25,37,60,66,71,78,87,92)

        val lsFl1 = listOf(21,62,86,98)
        val lsFl2 = listOf(20,80,103)
        val lsFl3 = listOf(109)
        val lsFl4 = listOf(13,18,34,85)
        val lsFl5 = listOf(15,26,31,46,83)
        val lsFl7 = listOf(96,11)

        for ((index, item) in answers.withIndex()) {
            if (item == 0) {
                val i = index + 1

                when {
                    lsTr1.contains(i) -> sc1++
                    lsTr2.contains(i) -> sc2++
                    lsTr3.contains(i) -> sc3++
                    lsTr4.contains(i) -> sc4++
                    lsTr5.contains(i) -> sc5++
                    lsTr6.contains(i) -> sc6++
                    lsTr7.contains(i) -> sc7++
                    lsTr8.contains(i) -> sc8++
                    lsTr9.contains(i) -> sc9++
                }
            }
        }

        for ((index, item) in answers.withIndex()) {
            if (item == 1) {
                val i = index + 1

                when {
                    lsFl1.contains(i) -> sc1++
                    lsFl2.contains(i) -> sc2++
                    lsFl3.contains(i) -> sc3++
                    lsFl4.contains(i) -> sc4++
                    lsFl5.contains(i) -> sc5++
                    lsFl7.contains(i) -> sc7++
                }
            }
        }

        return getSAResStr(sc1, sc2, sc3,
                sc4, sc5, sc6,
                sc7, sc8, sc9)
    }

    private fun getSAResStr (sc1: Int, sc2: Int, sc3: Int,
                             sc4: Int, sc5: Int, sc6: Int,
                             sc7: Int, sc8: Int, sc9: Int): ResultModel {
        val scSten1 = when (sc1) {
            0 -> 2
            1 -> 3
            in 2..3 -> 4
            in 4..5 -> 5
            in 6..7 -> 6
            8 -> 7
            9 -> 8
            10 -> 9
            11 -> 10
            else -> 0
        }
        val scSten2 = when (sc2) {
            in 0..1 -> 1
            2 -> 2
            in 3..4 -> 3
            in 5..6 -> 4
            in 7..8 -> 5
            9 -> 6
            in 10..11 -> 7
            12 -> 8
            13 -> 9
            14 -> 10
            else -> 0
        }
        val scSten3 = when (sc3) {
            in 0..1 -> 1
            2 -> 2
            3 -> 3
            in 4..5 -> 4
            6 -> 5
            7 -> 6
            8 -> 7
            in 9..10 -> 8
            11 -> 9
            12 -> 10
            else -> 0
        }
        val scSten4 = when (sc4) {
            0 -> 1
            1 -> 2
            2 -> 3
            in 3..4 -> 4
            5 -> 5
            in 6..7 -> 6
            8 -> 7
            9 -> 8
            10 -> 9
            11 -> 10
            else -> 0
        }
        val scSten5 = when (sc5) {
            in 0..1 -> 1
            2 -> 2
            3 -> 3
            in 4..5 -> 4
            in 6..7 -> 5
            8 -> 6
            in 9..10 -> 7
            11 -> 8
            12 -> 9
            in 13..14 -> 10
            else -> 0
        }
        val scSten6 = when (sc6) {
            in 0..1 -> 1
            2 -> 2
            in 3..4 -> 3
            5 -> 4
            in 6..7 -> 5
            8 -> 6
            9 -> 7
            10 -> 8
            11 -> 9
            12 -> 10
            else -> 0
        }
        val scSten7 = when (sc7) {
            0 -> 1
            1 -> 2
            2 -> 3
            3 -> 4
            in 4..5 -> 5
            6 -> 6
            in 7..8 -> 7
            9 -> 8
            10 -> 9
            11 -> 10
            else -> 0
        }
        val scSten8 = when (sc8) {
            0 -> 1
            1 -> 2
            2 -> 3
            in 3..4 -> 4
            in 5..7 -> 5
            in 8..10 -> 6
            in 11..12 -> 7
            13 -> 8
            14 -> 9
            15 -> 10
            else -> 0
        }
        val scSten9 = when (sc9) {
            0 -> 1
            1 -> 2
            2 -> 3
            in 3..4 -> 4
            5 -> 5
            in 6..7 -> 6
            8 -> 7
            9 -> 8
            10 -> 10
            else -> 0
        }

        val p1 = when (scSten1) {
            in 8..10 -> "У вас выраженное желание соответствовать общепринятым нормам поведения и взаимоотношений с окружающими людьми. У вас высокая мотивация получить социальное одобрение. Вы склонны избегать открытых отношений с самим собой. Причиной может быть или недостаточность навыков самонаблюдения, самоанализа, поверхностное видение себя, или осознанное нежелание раскрывать себя, признавать существование личных проблем."
            in 4..7 -> "Вы бываете честны и открыты с самим собой, вы умеете периодически наблюдать и анализировать свое поведение мысли, эмоции. Но при этом ждете социального одобрения, положительных оценок от окружающих людей. В критичных жизненных ситуациях может преобладать защитное поведение."
            in 1..3 -> "Вы внутренне честны и открыты с самим собой. Вы владеете навыками самонаблюдения и самоанализа, глубоко понимаете себя. Вы порой излишне критичны по отношению к себе. Во взаимоотношениях с людьми доминирует ориентация на собственное видение ситуации, происходящего."
            else -> ""
        }
        val p2 = when (scSten2) {
            in 8..10 -> "Вы уважаете себя, имеете выраженную самоуверенность, ощущение силы собственного \"Я\", высокую смелость в общении. У вас доминирует мотив успеха. Вы довольны собой, своими начинаниями и достижениями, ощущаете свою компетентность и способность решать многие жизненные вопросы. Препятствия на пути к достижению цели воспринимаете как преодолимые. Проблемы затрагивают неглубоко, переживаются недолго."
            in 4..7 -> "В привычных для себя ситуациях вы сохраняете работоспособность, уверенность в себе, ориентацию на успех начинаний. При неожиданном появлении трудностей уверенность в себе снижается, нарастают тревога, беспокойство. "
            in 1..3 -> "Вы не уважете себя, не уверены в своих возможностях, сомневаетесь в своих способностях. Вы не доверяете своим решениям, часто сомневаетесь в способности преодолевать трудности и препятствия, достигать намеченных целей. Возможно вы избегаете контактов с людьми, глубоко погружаетесь в собственные проблемы, испытываете внутреннюю напряженность."
            else -> ""
        }
        val p3 = when (scSten3) {
            in 8..10 -> "Основным источником развития своей личности, регулятором достижений и успехов вы считает себя самого. Вы ощущаете собственное \"Я\" как внутренний стержень, который координирует и направляет всю активность, организует поведение и отношения с людьми, что делает вас способным прогнозировать свои действия и последствия возникающих контактов с окружающими. Вы ощущаете себя способным оказывать сопротивление внешним влияниям, противиться судьбе и стихии событий. Вам свойственен контроль над эмоциональными реакциями и переживаниями по поводу себя."
            in 4..7 -> "Вы относитесь к своему \"Я\" в зависимости от степени адаптированности в ситуации. В привычных для себя условиях существования, в которых все возможные изменения знакомы и хорошо прогнозируемы, вы можете проявлять выраженную способность к личному контролю. В новых для себя ситуациях регуляционные возможности \"Я\" ослабевают, усиливается склонность к подчинению средовым воздействиям."
            in 1..3 -> "Вы верите в подвластность своего \"Я\" внешним обстоятельствам и событиям. Ваши механизмы саморегуляции ослаблены. Волевой контроль недостаточен для преодоления внешних и внутренних препятствий на пути к достижению цели. Основным источником происходящего вы считаете внешние обстоятельства. Причины, заключающиеся непосредственно в вашем поведении или мышлении, ваши защитные механизмы либо отрицают, либо вытесняют в подсознание. Переживания относительно собственного \"Я\" сопровождаются внутренним напряжением."
            else -> ""
        }
        val p4 = when (scSten4) {
            in 8..10 -> "Вы воспринимаете себя принятым окружающими людьми. Вы чувствуете, что вас любят другие, ценят за личностные и духовные качества, за совершаемые поступки и действия, за приверженность групповым нормам и правилам. Вы ощущаете в себе общительность, эмоциональную открытость для взаимодействия с окружающими, легкость установления деловых и личных контактов."
            in 4..7 -> "С вашей точки зрения, положительное отношение окружающих распространяется лишь на определенные качества, на определенные поступки; а другие личностные проявления способны вызывать у них раздражение и непринятие."
            in 1..3 -> "Вы относите себя к людям, неспособным вызвать уважение у окружающих. Вы считаете, что вызываете у других людей осуждение и порицание. Одобрение, поддержка от других не ожидаются."
            else -> ""
        }
        val p5 = when (scSten5) {
            in 8..10 -> "Вы высоко оцениваете свой духовный потенциал, богатство своего внутреннего мира. Вы склонны воспринимать себя как индивидуальность и высоко ценить собственную неповторимость. Уверенность в себе помогает вам противостоять средовым воздействиям, рационально воспринимать критику в свой адрес."
            in 4..7 -> "Вы избирательно относитесь к себе. Вы склонны высоко оценивать ряд своих качеств, признавать их уникальность. Другие же качества явно недооцениваются, поэтому замечания окружающих могут вызвать ощущение малоценности, личной несостоятельности."
            in 1..3 -> "Вы глубоко сомневаетесь в уникальности своей личности, недооцениваете свое духовное \"Я\". Неуверенность в себе ослабляет ваше сопротивление средовым влияниям. Повышенная чувствительность к замечаниям и критике окружающих в свой адрес делает вас обидчивым и ранимым, склонным не доверять своей индивидуальности."
            else -> ""
        }
        val p6 = when (scSten6) {
            in 8..10 -> "Вы склонны воспринимать все стороны своего \"Я\", принимать себя во всей полноте поведенческих проявлений. Общий фон восприятия себя положительный. Вы часто ощущаете симпатию к себе, ко всем качествам своей личности. Свои недостатки считаете продолжением достоинств. Неудачи, конфликтные ситуации не дают основания для того, чтобы считать, себя плохим человеком."
            in 4..7 -> "Вы избирательно относитесь к себе. Вы склонны принимать не все свои достоинства и критиковать не все свои недостатки."
            in 1..3 -> "У вас общий негативный фон восприятия себя, вы склонны воспринимать себя излишне критично. Симпатия к себе недостаточно выражена, проявляется эпизодически. Негативная оценка себя существует в разных формах: от описания себя в комическом свете до самоуничижения."
            else -> ""
        }
        val p7 = when (scSten7) {
            in 8..10 -> "Ваша \"Я\"-концепция высоко регидна, вы стремитесь сохранить в неизменном виде свои качества, требования к себе, а главное - видение и оценку себя. Ощущение самодостаточности и достижения идеала мешает реализации возможности саморазвития и самосовершенствования. Помехой для самораскрытия может быть также высокий уровень личностной тревожности, предрасположенность воспринимать окружающий мир как угрожающий самооценке."
            in 4..7 -> "Вы избирательно относитесь к своим личностным свойствам, стремитесь к изменению лишь некоторых своих качеств при сохранении прочих других."
            in 1..3 -> "У вас высокая готовность к изменению \"Я\"-концепции, открытость новому опыту познания себя, поиску соответствия реального и идеального \"Я\". Вы хотите развивать и совершенствовать собственное \"Я\", источником чего может быть, неудовлетворенность собой. Вы с легкостью изменяете представлений о себе."
            else -> ""
        }
        val p8 = when (scSten8) {
            in 8..10 -> "У вас преобладает негативный фон отношения к себе. Вы находитесь в состоянии постоянного контроля над своим \"Я\", стремитесь к глубокой оценке всего, что происходит в вашем внутреннем мире. Развитая рефлексия переходит в самокопание, приводящее к нахождению осуждаемых в себе качеств и свойств. Вы высоко требовательны к себе, что нередко приводит к конфликту между \"Я\" реальным и \"Я\" идеальным, между уровнем притязаний и фактическими достижениями, к признанию своей малоценности. Истинным источником своих достижений и неудач вы считаете преимущественно себя."
            in 4..7 -> "Ваше отношение к себе, установка видеть себя зависит от степени адаптированности в ситуации. В привычных для себя условиях, особенности которых хорошо знакомы и прогнозируемы, наблюдаются положительный фон отношения к себе, признание своих достоинств и высокая оценка своих достижений. Неожиданные трудности, возникающие дополнительные препятствия могут способствовать усилению недооценки собственных успехов."
            in 1..3 -> "Вы в целом положительно относитесь к себе, ощущаете баланс между собственными возможностями и требованиями окружающей реальности, между притязаниями и достижениями, довольны сложившейся жизненной ситуацией и собой. При этом возможны отрицание своих проблем и поверхностное восприятие себя."
            else -> ""
        }
        val p9 = when (scSten9) {
            in 8..10 -> "Вы видите в себе прежде всего недостатки, вы готовы поставить себе в вину все свои промахи и неудачи. Проблемные ситуации, конфликты в сфере общения актуализируют сложившиеся психологические защиты, среди которых доминируют реакции защиты собственного \"Я\" в виде порицания, осуждения себя или привлечения смягчающих обстоятельств. Установка на самообвинение сопровождается развитием внутреннего напряжения, ощущением невозможности удовлетворения основных потребностей."
            in 4..7 -> "У вас избирательное отношение к себе. Обвинение себя за те или иные поступки и действия сочетается с выражением гнева, досады в адрес окружающих."
            in 1..3 -> "У вас имеется тенденция к отрицанию собственной вины в конфликтных ситуациях. Защита собственного \"Я\" осуществляется путем обвинения преимущественно других, перенесением ответственности на окружающих за устранение барьеров на пути к достижению цели. Ощущение удовлетворенности собой сочетается с порицанием других, поисками в них источников всех неприятностей и бед."
            else -> ""
        }

        return ResultModel(Date(),
            listOf(
                Pair("Закрытость", p1),
                Pair("Самоуверенность", p2),
                Pair("Саморуководство", p3),
                Pair("Отраженное самоотношение", p4),
                Pair("Самоценность", p5),
                Pair("Самопринятие", p6),
                Pair("Самопривязанность", p7),
                Pair("Внутренняя конфликтность", p8),
                Pair("Самообвинение", p9)
            ))
    }

    /*
    * Helper functions
    *
    * */
    private fun reverceInt(value: Int, max: Int): Int {
        return max + 1 - value
    }
}