package com.morozov.psychology.domain.implementation.examples

import com.morozov.psychology.domain.interfaces.examples.ExperimentsLoader

class ExperimentsLoaderImpl: ExperimentsLoader {

    override fun getExperiments(): List<String> {
        return listOf("Эксперимент №1", "Эксперимент №2", "Эксперимент №3"
            , "Эксперимент №4", "Эксперимент №5")
    }
}