package com.morozov.psychology.mvp.models.tests

import java.util.*

class ResultModel(val date: Date, val items: List<Pair<String, String>>, var firebaseRes: Map<String, String> = mapOf(), var general: Int? = null)