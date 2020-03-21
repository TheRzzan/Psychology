package com.morozov.psychology.domain.implementation.tests

import android.content.Context
import com.morozov.psychology.domain.interfaces.tests.*
import com.morozov.psychology.mvp.models.tests.QuestionModel
import com.morozov.psychology.mvp.models.tests.ResultModel
import com.morozov.psychology.mvp.models.tests.TestModel
import com.morozov.psychology.mvp.models.tests.about.AboutModel
import com.morozov.psychology.mvp.models.tests.about.enums.*
import com.morozov.psychology.utility.AppConstants
import com.morozov.psychology.utility.MySharedPreferences
import com.morozov.psychology.utility.TestsResultsDBHelper

class TestsAllImpl(private val context: Context): DescriptionLoader, QuestionsLoader, ResultsLoader, ResultSaver, AboutLoader, AboutSaver {

    companion object {
        var testsList: MutableList<TestModel> = mutableListOf()
        var aboutModel: AboutModel? = null
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
                loadWeismanBackQuestions(),
                mutableListOf())

            val test2 = TestModel("Тест Эллиса", "У людей бывают разные убеждения. Нас интересует Ваше мнение относительно утверждений, приведенных ниже. Пожалуйста, оцените степень своего согласия с данными утверждениями. Помните, что правильных ответов не существует.",
                loadEllisQuestions(),
                mutableListOf())

            val test3 = TestModel("Госпитальная шкала тревоги и депрессии", "Прочитайте внимательно каждое утверждение и выберите ответ, который в наибольшей степени соответствует тому, как вы себя чувствовали на прошлой неделе. Не раздумывайте слишком долго над каждым утверждением. Ваша первая реакция всегда будет более верной.",
                loadHospitalScaleQuestions(),
                mutableListOf())

            val test4 = TestModel("Интегративный тест тревожности", "Вам будут предложены несколько утверждений, касающихся Вашего эмоционального состояния. В отношении каждого из них нужно решить – насколько данное состояние выражено именно сейчас, в данный момент, сегодня. Выберите степень выраженности. Далее Вам будут предложены еще несколько утверждений, касающихся Вашего эмоционального состояния. \n" +
                    "В отношении каждого из них нужно решить – как часто на протяжении последнего года Вы испытывали это. \n",
                loadIntegrativeQuestions(),
                mutableListOf())

            val test5 = TestModel("Опросник «Способы совладающего поведения» Лазаруса", "Прочитайте утверждения и определите, насколько часто вы поступаете так, как описано, оказавшись в трудной ситуации.",
                loadLazarusQuestionnaireQuestions(),
                mutableListOf())

            val test6 = TestModel("Индекс жизненного стиля", "Прочтите следующие утверждения. Эти утверждения описывают чувства, которые человек ОБЫЧНО испытывает, или действия, которые он ОБЫЧНО совершает. Ответьте, соответствует вам утверждение или нет. \n",
                loadStyleIndexQuestions(),
                mutableListOf())

            val test7 = TestModel("Методика исследования самоотношения", "Вам предлагается перечень суждений, характеризующих отношение человека к себе, к своим поступкам и действиям. Внимательно прочитайте каждое суждение. Если Вы согласны с содержанием суждения, то выберите да, если не согласны - нет. Работайте быстро и внимательно, не пропускайте ни одного суждения. Возможно, что некоторые суждения покажутся вам излишне личными, затрагивающими интимные стороны вашей личности. Постарайтесь определить их соответствие себе как можно искренне. Ваши ответы никому не будут демонстрироваться.",
                loadSelfAttitudeQuestions(),
                mutableListOf())

            testsList = mutableListOf(test1, test2, test3, test4, test5, test6, test7)

            val thinkDBHelper = TestsResultsDBHelper(context)
            val count = thinkDBHelper.getCount()

            var i = 0
            while (i < count) {
                val itemAt = thinkDBHelper.getItemAt(i)

                if (itemAt != null)
                    getTestByName(itemAt.first)?.results?.add(itemAt.second)

                i++
            }
        }
    }

    override fun getDescription(testName: String): Pair<String, String> {
        return Pair(getTestByName(testName)!!.name, getTestByName(testName)!!.description)
    }

    override fun nextTest(testName: String): String? {
        return getNextTestByName(testName)
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
        val thinkDBHelper = TestsResultsDBHelper(context)
        thinkDBHelper.addResult(testName, result)
    }

    override fun getAboutModel(): AboutModel? {
        val strPreference = MySharedPreferences.getStrPreference(context, AppConstants.PREF_ABOUT_MEDICINES)
        val split = strPreference.split(" ")
        val list = mutableListOf<MedicinesEnum>()
        for (item in split) {
            when (item) {
                MedicinesEnum.ANTIDEPRESSANTS.name -> list.add(MedicinesEnum.ANTIDEPRESSANTS)
                MedicinesEnum.TRANQUILIZERS.name -> list.add(MedicinesEnum.TRANQUILIZERS)
                MedicinesEnum.ANTIPSYCHOTICS.name -> list.add(MedicinesEnum.ANTIPSYCHOTICS)
                MedicinesEnum.NORMOTIMICS.name -> list.add(MedicinesEnum.NORMOTIMICS)
            }
        }
        val tmp = Pair(
            list,
            mutableListOf(when (MySharedPreferences.getStrPreference(context, AppConstants.PREF_ABOUT_MEDICINES_CUSTOM)) {
                AppConstants.EMPTY_PREF -> ""
                else -> MySharedPreferences.getStrPreference(context, AppConstants.PREF_ABOUT_MEDICINES_CUSTOM)
            })
        )

        aboutModel = AboutModel(
            when (MySharedPreferences.getStrPreference(context, AppConstants.PREF_ABOUT_SEX)) {
                SexEnum.WOMAN.name -> SexEnum.WOMAN
                SexEnum.MAN.name -> SexEnum.MAN
                else -> null
            },
            MySharedPreferences.getIntPreference(context, AppConstants.PREF_ABOUT_AGE),
            when (MySharedPreferences.getStrPreference(context, AppConstants.PREF_ABOUT_MARITAL)) {
                MaritalStatusEnum.SINGLE.name -> MaritalStatusEnum.SINGLE
                MaritalStatusEnum.MARRIED.name -> MaritalStatusEnum.MARRIED
                MaritalStatusEnum.DIVORCED.name -> MaritalStatusEnum.DIVORCED
                MaritalStatusEnum.WIDOWER.name -> MaritalStatusEnum.WIDOWER
                else -> null
            },
            when (MySharedPreferences.getStrPreference(context, AppConstants.PREF_ABOUT_EDUCATION)) {
                EducationEnum.PRIMARY.name -> EducationEnum.PRIMARY
                EducationEnum.SECONDARY.name -> EducationEnum.SECONDARY
                EducationEnum.SECONDARY_VOCATIONAL.name -> EducationEnum.SECONDARY_VOCATIONAL
                EducationEnum.HIGHER_VOCATIONAL.name -> EducationEnum.HIGHER_VOCATIONAL
                else -> null
            },
            MySharedPreferences.getIntPreference(context, AppConstants.PREF_ABOUT_TIME_OF_USE),
            when (MySharedPreferences.getStrPreference(context, AppConstants.PREF_ABOUT_FREQUENCY_OF_USE)) {
                FrequencyOfUseEnum.EVERYDAY.name -> FrequencyOfUseEnum.EVERYDAY
                FrequencyOfUseEnum.EVERYWEEK.name -> FrequencyOfUseEnum.EVERYWEEK
                FrequencyOfUseEnum.EVERYMONTH.name -> FrequencyOfUseEnum.EVERYMONTH
                FrequencyOfUseEnum.LESS_OFTEN.name -> FrequencyOfUseEnum.LESS_OFTEN
                else -> null
            },
            MySharedPreferences.getBoolPreference(context, AppConstants.PREF_ABOUT_IS_VISIT_THERAPY),
            MySharedPreferences.getBoolPreference(context, AppConstants.PREF_ABOUT_IS_VISIT_PSYCHOLOGY),
            MySharedPreferences.getIntPreference(context, AppConstants.PREF_ABOUT_TIME_OF_PSYTER_VISIT),
            MySharedPreferences.getIntPreference(context, AppConstants.PREF_ABOUT_TIME_OF_PSY_VISIT),
            when (MySharedPreferences.getStrPreference(context, AppConstants.PREF_ABOUT_FREQUENCY_OF_THERAPY)) {
                FrequencyOfTherapyEnum.DONT_APPEAL.name -> FrequencyOfTherapyEnum.DONT_APPEAL
                FrequencyOfTherapyEnum.FEW_TIMES_A_WEEK.name -> FrequencyOfTherapyEnum.FEW_TIMES_A_WEEK
                FrequencyOfTherapyEnum.ONE_TIME_A_WEEK.name -> FrequencyOfTherapyEnum.ONE_TIME_A_WEEK
                FrequencyOfTherapyEnum.FEW_TIMES_A_MONTH.name -> FrequencyOfTherapyEnum.FEW_TIMES_A_MONTH
                FrequencyOfTherapyEnum.LESS_OFTEN.name -> FrequencyOfTherapyEnum.LESS_OFTEN
                else -> null
            },
            when (MySharedPreferences.getBoolPreference(context, AppConstants.PREF_ABOUT_MEDICINES_NO)) {
                true -> null
                false -> tmp
            } ,
            MySharedPreferences.getBoolPreference(context, AppConstants.PREF_DIARY),
            MySharedPreferences.getStrPreferenceNullable(context, AppConstants.PREF_ABOUT_EMAil)
        )
        return aboutModel
    }

    override fun saveAboutModel(about: AboutModel) {
        about.sex?.name?.let { MySharedPreferences.setPreference(context, AppConstants.PREF_ABOUT_SEX, it) }
        about.age?.let { MySharedPreferences.setPreference(context, AppConstants.PREF_ABOUT_AGE, it) }
        about.maritalStatus?.name?.let { MySharedPreferences.setPreference(context, AppConstants.PREF_ABOUT_MARITAL, it) }
        about.education?.name?.let { MySharedPreferences.setPreference(context, AppConstants.PREF_ABOUT_EDUCATION, it) }
        about.timeOfUse?.let { MySharedPreferences.setPreference(context, AppConstants.PREF_ABOUT_TIME_OF_USE, it) }
        about.frequencyOfUse?.name?.let { MySharedPreferences.setPreference(context, AppConstants.PREF_ABOUT_FREQUENCY_OF_USE, it) }
        about.isVisitTherapy?.let { MySharedPreferences.setPreference(context, AppConstants.PREF_ABOUT_IS_VISIT_THERAPY, it) }
        about.isVisitPsychology?.let { MySharedPreferences.setPreference(context, AppConstants.PREF_ABOUT_IS_VISIT_PSYCHOLOGY, it) }
        about.timeOfPsychologistVisit?.let { MySharedPreferences.setPreference(context, AppConstants.PREF_ABOUT_TIME_OF_PSY_VISIT, it) }
        about.timeOfPsychoterapevtVisit?.let { MySharedPreferences.setPreference(context, AppConstants.PREF_ABOUT_TIME_OF_PSYTER_VISIT, it) }
        about.frequencyOfTherapy?.name?.let { MySharedPreferences.setPreference(context, AppConstants.PREF_ABOUT_FREQUENCY_OF_THERAPY, it) }
        about.agreeToSendMyTestInfo?.let { MySharedPreferences.setPreference(context, AppConstants.PREF_DIARY, it) }
        about.email?.let { MySharedPreferences.setPreference(context, AppConstants.PREF_ABOUT_EMAil, it) }

        if (about.medicines != null) {
            MySharedPreferences.setPreference(context, AppConstants.PREF_ABOUT_MEDICINES_NO, false)

            val first = about.medicines?.first
            var tmpStr = ""

            if (first != null)
                for (item in first) {
                    tmpStr += "${item.name} "
                }

            if (tmpStr != "")
                MySharedPreferences.setPreference(context, AppConstants.PREF_ABOUT_MEDICINES, tmpStr)
            about.medicines?.second?.get(0)?.let { MySharedPreferences.setPreference(context, AppConstants.PREF_ABOUT_MEDICINES_CUSTOM, it) }
        } else {
            MySharedPreferences.setPreference(context, AppConstants.PREF_ABOUT_MEDICINES_NO, true)
        }
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

    private fun getNextTestByName(testName: String): String? = when (testName) {
        AppConstants.WEISMAN_BACK_TEST -> AppConstants.ELLIS_TEST
        AppConstants.ELLIS_TEST -> AppConstants.HOSPITAL_SCALE_TEST
        AppConstants.HOSPITAL_SCALE_TEST -> AppConstants.INTEGRATIVE_TEST
        AppConstants.LAZARUS_QUESTIONNAIRE_TEST -> AppConstants.STYLE_INDEX_TEST
        AppConstants.STYLE_INDEX_TEST -> AppConstants.SELF_ATTITUDE_TEST
        AppConstants.INTEGRATIVE_TEST -> AppConstants.LAZARUS_QUESTIONNAIRE_TEST
        else -> null
    }

    /*
    * Tests loader helpers
    *
    * */
    private fun loadWeismanBackQuestions(): List<QuestionModel> {
        val answers = listOf("Полностью согласен", "В основном согласен",
            "Скорее согласен", "Трудно определить", "Скорее не согласен", "В  основном  не  согласен",
            "Полностью  не  согласен")

        val fileName = "tests_weisman_back"
        val content = context.assets.open(fileName).bufferedReader().use {
            it.readText()
        }

        val contentQuestions = content.split("\n")
        val questionList = mutableListOf<QuestionModel>()

        for (item in contentQuestions) {
            questionList.add(QuestionModel(item, answers))
        }

        return questionList
    }

    private fun loadEllisQuestions(): List<QuestionModel> {
        val answers = listOf("Полностью согласен", "В основном согласен",
            "Слегка согласен", "Слегка не согласен", "В  основном  не  согласен",
            "Полностью  не  согласен")

        val fileName = "tests_ellis"
        val content = context.assets.open(fileName).bufferedReader().use {
            it.readText()
        }

        val contentQuestions = content.split("\n")
        val questionList = mutableListOf<QuestionModel>()

        for (item in contentQuestions) {
            questionList.add(QuestionModel(item, answers))
        }

        return questionList
    }

    private fun loadHospitalScaleQuestions(): List<QuestionModel> {
        val questionList = mutableListOf<QuestionModel>()

        val question1 = QuestionModel("Я напряжен. Мне не по себе.",
            listOf("все время", "часто", "время от времени, иногда", "совсем не испытываю"))

        val question2 = QuestionModel("То, что приносило мне большое удовольствие, и сейчас вызывает такое же чувство.",
            listOf( "определенно это так", "наверное, это так",
                "лишь в очень малой степени это так", "это совсем не так"))

        val question3 = QuestionModel("Мне страшно. Кажется, будто что-то ужасное может вот-вот случиться.",
            listOf( "определенно это так, и страх очень сильный", "да, это так, но страх не очень сильный",
                "иногда, но это меня не беспокоит", "совсем не испытываю"))

        val question4 = QuestionModel("Я способен рассмеяться и увидеть в том или ином событии смешное.",
            listOf( "определенно, это так", "наверное, это так",
                "лишь в очень малой степени это так", "совсем не способен"))

        val question5 = QuestionModel("Беспокойные мысли крутятся у меня в голове.",
            listOf( "постоянно", "большую часть времени",
                "время от времени", "только иногда"))

        val question6 = QuestionModel("Я чувствую себя бодрым.",
            listOf( "совсем не чувствую", "очень редко", "иногда", "практически все время"))

        val question7 = QuestionModel("Я легко могу сесть и расслабиться.",
            listOf( "определенно, это так", "наверное, это так", "лишь изредка это так", "совсем не могу"))

        val question8 = QuestionModel("Мне кажется, что я стал все делать очень медленно.",
            listOf( "практически все время", "часто", "иногда", "совсем нет"))

        val question9 = QuestionModel("Я испытываю внутренне напряжение или дрожь.",
            listOf( "совсем не испытываю", "иногда", "часто", "очень часто"))

        val question10 = QuestionModel("Я не слежу за своей внешностью.",
            listOf( "определенно это так", "я не уделяю этому столько времени, сколько нужно",
                "может быть, я стал меньше уделять этому внимания", "я слежу за собой так же, как и раньше"))

        val question11 = QuestionModel("Я не могу усидеть на месте, словно мне постоянно нужно двигаться.",
            listOf( "определенно, это так", "наверное, это так",
                "лишь в очень малой степени это так", "совсем не способен"))

        val question12 = QuestionModel("Я считаю, что мои дела (занятия, увлечения) могут принести мне чувство удовлетворения.",
            listOf( "точно так, как и обычно", "да, но не в той степени, как раньше",
                "значительно меньше, чем раньше", "совсем не считаю"))

        val question13 = QuestionModel("У меня бывает внезапное чувство паники.",
            listOf( "действительно, очень часто", "довольно часто",
                "не так уж часто", "совсем не бывает"))

        val question14 = QuestionModel("Я могу получить удовольствие от хорошей книги, фильма, радио- или телепрограммы.",
            listOf( "часто", "иногда", "редко", "очень редко"))

        questionList.add(question1)
        questionList.add(question2)
        questionList.add(question3)
        questionList.add(question4)
        questionList.add(question5)
        questionList.add(question6)
        questionList.add(question7)
        questionList.add(question8)
        questionList.add(question9)
        questionList.add(question10)
        questionList.add(question11)
        questionList.add(question12)
        questionList.add(question13)
        questionList.add(question14)

        return questionList
    }

    private fun loadIntegrativeQuestions(): List<QuestionModel> {
        val answers1 = listOf("Совсем нет", "Слабо выражено",
            "Выражено", "Очень выражено")

        val answers2 = listOf("Никогда", "Редко",
            "Часто", "Почти всё время")

        val fileName = "tests_integrative"
        val content = context.assets.open(fileName).bufferedReader().use {
            it.readText()
        }

        val contentQuestions = content.split("\n")
        val questionList = mutableListOf<QuestionModel>()

        for (item in contentQuestions) {
            questionList.add(QuestionModel("В данный момент:\n\n$item", answers1))
        }

        for (item in contentQuestions) {
            questionList.add(QuestionModel("На протяжении последнего года:\n\n$item", answers2))
        }

        return questionList
    }

    private fun loadLazarusQuestionnaireQuestions(): List<QuestionModel> {
        val answers = listOf("Никогда", "Редко",
            "Иногда", "Часто")

        val fileName = "tests_lazarus_questionnaire"
        val content = context.assets.open(fileName).bufferedReader().use {
            it.readText()
        }

        val contentQuestions = content.split("\n")
        val questionList = mutableListOf<QuestionModel>()

        for (item in contentQuestions) {
            questionList.add(QuestionModel(item, answers))
        }

        return questionList
    }

    private fun loadStyleIndexQuestions(): List<QuestionModel> {
        val answers = listOf("Да", "Нет")

        val fileName = "tests_style_index"
        val content = context.assets.open(fileName).bufferedReader().use {
            it.readText()
        }

        val contentQuestions = content.split("\n")
        val questionList = mutableListOf<QuestionModel>()

        for (item in contentQuestions) {
            questionList.add(QuestionModel(item, answers))
        }

        return questionList
    }

    private fun loadSelfAttitudeQuestions(): List<QuestionModel> {
        val answers = listOf("Да", "Нет")

        val fileName = "tests_self_attitude"
        val content = context.assets.open(fileName).bufferedReader().use {
            it.readText()
        }

        val contentQuestions = content.split("\n")
        val questionList = mutableListOf<QuestionModel>()

        for (item in contentQuestions) {
            questionList.add(QuestionModel(item, answers))
        }

        return questionList
    }
}