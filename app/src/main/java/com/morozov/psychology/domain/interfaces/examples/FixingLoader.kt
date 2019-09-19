package com.morozov.psychology.domain.interfaces.examples

import com.morozov.psychology.mvp.models.examples.FixingExerciseModel

interface FixingLoader {

    fun getFixings(): List<FixingExerciseModel>
}