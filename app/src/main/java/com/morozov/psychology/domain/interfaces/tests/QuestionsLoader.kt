package com.morozov.psychology.domain.interfaces.tests

import com.morozov.psychology.mvp.models.tests.QuestionModel

interface QuestionsLoader {

    fun getQuestions(testName: String): List<QuestionModel>
}