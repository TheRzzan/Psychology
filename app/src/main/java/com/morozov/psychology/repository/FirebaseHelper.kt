package com.morozov.psychology.repository

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

object FirebaseHelper {
    private var database: DatabaseReference? = null
    private fun init() {
        database = FirebaseDatabase.getInstance().reference
    }

    fun writeTest(nameTest: String, date: String, answers: Map<String, String>) {
        val answers = FirebaseAnswers(nameTest, date, answers)
        if (database == null)
            init()
        database!!.child("analytics").push().setValue(answers)
    }

    private data class FirebaseAnswers(val nameTest: String, val date: String, val answers: Map<String, String>)
}
