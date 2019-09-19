package com.morozov.psychology.mvp.models.examples

import android.graphics.drawable.VectorDrawable

data class ExperimentModel(val title: String, val drawable: VectorDrawable, val description: String,
                           val question: String, val variants: List<String>, val conclusion: String)