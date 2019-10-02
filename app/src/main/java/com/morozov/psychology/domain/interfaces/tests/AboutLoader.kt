package com.morozov.psychology.domain.interfaces.tests

import com.morozov.psychology.mvp.models.tests.about.AboutModel

interface AboutLoader {

    fun getAboutModel(): AboutModel?
}