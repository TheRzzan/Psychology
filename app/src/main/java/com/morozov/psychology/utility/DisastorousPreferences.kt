package com.morozov.psychology.utility

import android.content.Context

object DisastorousPreferences {
    private const val WORST_TXT = "worst_txt"
    private const val BEST_TXT = "best_txt"
    private const val WORST_PERC = "worst_perc"
    private const val BEST_PERC = "best_perc"

    fun saveWorstDis(context: Context, text: String = AppConstants.EMPTY_PREF, percent: Int = -1) {
        MySharedPreferences.setPreference(context, WORST_TXT, text)
        MySharedPreferences.setPreference(context, WORST_PERC, percent)
    }

    fun saveBestDis(context: Context, text: String = AppConstants.EMPTY_PREF, percent: Int = -1) {
        MySharedPreferences.setPreference(context, BEST_TXT, text)
        MySharedPreferences.setPreference(context, BEST_PERC, percent)
    }

    fun getWorstDis(context: Context): Pair<String, Int>? {
        val worst = MySharedPreferences.getStrPreference(context, WORST_TXT)

        return if (worst == AppConstants.EMPTY_PREF)
            null
        else
            Pair(worst, MySharedPreferences.getIntPreference(context, WORST_PERC))
    }

    fun getBestDis(context: Context): Pair<String, Int>? {
        val best = MySharedPreferences.getStrPreference(context, BEST_TXT)

        return if (best == AppConstants.EMPTY_PREF)
            null
        else
            Pair(best, MySharedPreferences.getIntPreference(context, BEST_PERC))
    }
}