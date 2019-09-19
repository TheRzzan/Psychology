package com.morozov.psychology.mvp.models.examples

import android.graphics.drawable.VectorDrawable

data class FixingExerciseModel(val title: String, val drawable: VectorDrawable, val description: String,
                               val fixings: List<FixingModel>)