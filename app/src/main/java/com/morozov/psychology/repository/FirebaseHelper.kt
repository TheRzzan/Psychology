package com.morozov.psychology.repository

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.morozov.psychology.mvp.models.tests.about.AboutModel

object FirebaseHelper {
    private var database: DatabaseReference? = null
    private fun init() {
        database = FirebaseDatabase.getInstance().reference
    }

    fun writeTest(nameTest: String, date: String, answers: Map<String, String>, general: Int? = null, userModel: AboutModel?) {
        val answersHashMap = mutableMapOf<String, Any?>()
        answersHashMap["nameTest"] = nameTest
        answersHashMap["date"] = date
        answersHashMap["userModel"] = userModel
        val testObject = mutableMapOf<String, Any?>()
        testObject["results"] = answers
        testObject["general"] = general
        answersHashMap[nameTest] = testObject
        if (database == null)
            init()
        database!!.child("analytics").push().setValue(answersHashMap)
    }
}
