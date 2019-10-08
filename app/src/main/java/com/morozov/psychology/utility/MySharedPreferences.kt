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

    fun getPreference(context: Context, pref: String): String {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        return preferences.getString(pref, AppConstants.EMPTY_PREF)
    }
}