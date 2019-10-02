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
            val questionModel = QuestionModel(
                "Трудно быть счастливым, если ты не красив, не интеллигентен, не богат и не имеешь творческих способностей.",
                listOf(
                    "Полностью согласен", "В основном согласен",
                    "Скорее согласен", "Трудно определить", "Скорее не согласен"
                )
            )

            val test1 = TestModel("Шкала дисфункциональных отношений Вейсмана – Бека", "Этот  опросник  содержит  ряд  мнений,  позиций  и  принципов,  которых  иногда  придерживаются  люди.  Прочтите,  пожалуйста,  каждый  пункт  внимательно  и  решите  затем,  в  какой  степени  Вы  согласны  или  не  согласны  с  каждым  высказыванием.  \n" +
                    "\n" +
                    "Убедитесь,  что  для  каждого  высказывания  Вы  дали  только  один  ответ.  Поскольку  все  люди  разные,  то  здесь  не  может  быть  правильных  или  неправильных  ответов.  При  принятии  решения  о  том,  верно  ли,  с  Вашей  точки  зрения,  то  или  иное  утверждение,  просто  подумайте  о  том,  какой  Вы  есть  или  как  Вы  думаете  обычно  или  чаще  всего.\n",
                listOf(questionModel, questionModel, questionModel),
                mutableListOf())

            val test2 = TestModel("Тест Эллиса", "У людей бывают разные убеждения. Нас интересует Ваше мнение относительно утверждений, приведенных ниже. Пожалуйста, оцените степень своего согласия с данными утверждениями. Помните, что правильных ответов не существует.",
                listOf(questionModel, questionModel),
                mutableListOf())

            val test3 = TestModel("Госпитальная шкала тревоги и депрессии", "Прочитайте внимательно каждое утверждение, и выберите ответ, который в наибольшей степени соответствует тому, как Вы себя чувствовали на прошлой неделе. Не раздумывайте слишком долго над каждым утверждением. Ваша первая реакция всегда будет более верной.",
                listOf(questionModel, questionModel, questionModel, questionModel),
                mutableListOf())

            val test4 = TestModel("Интегративный тест тревожности", "Вам будут предложены несколько утверждений, касающихся Вашего эмоционального состояния. В отношении каждого из них нужно решить – насколько данное состояние выражено именно сейчас, в данный момент, сегодня. Выберите степень выраженности.",
                listOf(questionModel, questionModel, questionModel, questionModel),
                mutableListOf())

            val test5 = TestModel("Опросник «Способы совладающего поведения» Лазаруса", "Прочитайте утверждения и определите, насколько часто вы поступаете так, как описано, оказавшись в трудной ситуации.",
                listOf(questionModel, questionModel, questionModel, questionModel),
                mutableListOf())

            val test6 = TestModel("Индекс жизненного стиля", "Прочтите следующие утверждения. Эти утверждения описывают чувства,\n" +
                    "которые человек ОБЫЧНО испытывает, или действия, которые он ОБЫЧНО\n" +
                    "совершает. Ответьте, соответствует вам утверждение или нет. \n",
                listOf(questionModel, questionModel, questionModel, questionModel),
                mutableListOf())

            val test7 = TestModel("Методика исследования самоотношения", "Вам предлагается перечень суждений, характеризующих отношение человека к себе, к своим поступкам и действиям. Внимательно прочитайте каждое суждение. Если Вы согласны с содержанием суждения, то выберите да, если несогласны - нет. Работайте быстро и внимательно, не пропускайте ни одного суждения. Возможно, что некоторые суждения покажутся вам излишне личными, затрагивающими интимные стороны вашей личности. Постарайтесь определить их соответствие себе как можно искренне. Ваши ответы никому не будут демонстрироваться.",
                listOf(questionModel, questionModel, questionModel, questionModel),
                mutableListOf())

            testsList = mutableListOf(test1, test2, test3, test4, test5, test6, test7)
        }
    }

    override fun getDescription(testName: String): Pair<String, String> {
        return Pair(getTestByName(testName)!!.name, getTestByName(testName)!!.description)
    }

    override fun getQuestions(testName: String): List<QuestionModel> {
        return getTestByName(testName)!!.questions
    }

    override fun getAllResults(testName: String): Pair<String, List<ResultModel>> {
        return Pair(getTestByName(testName)!!.name, getTestByName(testName)!!.results)
    }

    override fun getLastResult(testName: String): Pair<String, ResultModel?> {
        if (getTestByName(testName)!!.results.isEmpty())
            return Pair(getTestByName(testName)!!.name, null)
        else
            return Pair(getTestByName(testName)!!.name, getTestByName(testName)!!.results[getTestByName(testName)!!.results.size - 1])
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
        AppConstants.LAZARUS_QUESTIONNAIRE_TEST -> testsList[4]
        AppConstants.SELF_ATTITUDE_TEST -> testsList[6]
        AppConstants.STYLE_INDEX_TEST -> testsList[5]
        AppConstants.INTEGRATIVE_TEST -> testsList[3]
        else -> null
    }
}