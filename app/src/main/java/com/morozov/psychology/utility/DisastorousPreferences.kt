package com.morozov.psychology.utility

import android.content.Context

object DisastorousPreferences {
    private const val WORST_TXT = "worst_txt"
    private const val BEST_TXT = "best_txt"
    private const val WORST_PERC = "worst_perc"
    private const val BEST_PERC = "best_perc"

    data class Dis1 (val good: String = AppConstants.EMPTY_PREF,
                      val think: String = AppConstants.EMPTY_PREF,
                      val forecast: String = AppConstants.EMPTY_PREF)

    private const val GOOD_OUTCOME = "good_outcome"
    private const val THINK = "think"
    private const val FORECAST = "forecast"

    fun saveDis1(context: Context,
                 dis1: Dis1) {
        MySharedPreferences.setPreference(context, GOOD_OUTCOME, dis1.good)
        MySharedPreferences.setPreference(context, THINK, dis1.think)
        MySharedPreferences.setPreference(context, FORECAST, dis1.forecast)
    }

    fun getDis1(context: Context): Dis1? {
        val good = MySharedPreferences.getStrPreference(context, GOOD_OUTCOME)
        val think = MySharedPreferences.getStrPreference(context, THINK)
        val forecast = MySharedPreferences.getStrPreference(context, FORECAST)

        return if (good == AppConstants.EMPTY_PREF ||
                   think == AppConstants.EMPTY_PREF ||
                   forecast == AppConstants.EMPTY_PREF)
            null
        else
            Dis1(good, think, forecast)
    }

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