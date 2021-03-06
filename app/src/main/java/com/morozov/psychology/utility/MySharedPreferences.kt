@file:Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package com.morozov.psychology.utility

import android.content.Context
import android.preference.PreferenceManager

object MySharedPreferences {

    fun setPreference(context: Context, pref: String, value: String) {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = preferences.edit()
        editor.putString(pref, value)
        editor.apply()
    }

    fun setPreference(context: Context, pref: String, value: Boolean) {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = preferences.edit()
        editor.putBoolean(pref, value)
        editor.apply()
    }

    fun setPreference(context: Context, pref: String, value: Int) {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = preferences.edit()
        editor.putInt(pref, value)
        editor.apply()
    }

    fun getStrPreference(context: Context, pref: String): String {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        return preferences.getString(pref, AppConstants.EMPTY_PREF)
    }

    fun getStrPreferenceNullable(context: Context, pref: String): String? {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        return preferences.getString(pref, null)
    }

    fun getBoolPreference(context: Context, pref: String): Boolean {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        return preferences.getBoolean(pref, false)
    }

    fun getBoolPreferenceTrue(context: Context, pref: String): Boolean {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        return preferences.getBoolean(pref, true)
    }

    fun getIntPreference(context: Context, pref: String): Int {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        return preferences.getInt(pref, 0)
    }
}