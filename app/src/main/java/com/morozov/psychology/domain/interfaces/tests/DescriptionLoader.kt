package com.morozov.psychology.domain.interfaces.tests

interface DescriptionLoader {

    fun getDescription(testName: String): Pair<String, String>

    fun nextTest(testName: String): String?
}