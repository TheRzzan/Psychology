package com.morozov.psychology.mvp.models.tests

data class TestModel(val name: String, val description: String,
                     val questions: List<QuestionModel>,
                     val results: List<ResultModel>)
