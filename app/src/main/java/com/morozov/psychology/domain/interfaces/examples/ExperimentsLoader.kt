package com.morozov.psychology.domain.interfaces.examples

import com.morozov.psychology.mvp.models.examples.ExperimentModel

interface ExperimentsLoader {

    fun getExperiments(): List<ExperimentModel>
}