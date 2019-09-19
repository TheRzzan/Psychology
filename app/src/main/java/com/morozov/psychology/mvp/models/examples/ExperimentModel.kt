package com.morozov.psychology.mvp.models.examples

data class ExperimentModel(val title: String, val description: String,
                           val question: String, val variants: List<String>, val conclusion: String)