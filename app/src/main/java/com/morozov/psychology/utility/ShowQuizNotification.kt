package com.morozov.psychology.utility

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.support.v4.app.NotificationCompat
import android.util.Log
import com.morozov.psychology.R
import com.morozov.psychology.ui.activities.MainActivity

class ShowQuizNotification : Service() {

    override fun onCreate() {
        super.onCreate()

        val mainIntent = Intent(this, MainActivity::class.java)

        val notificationManager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val noti = NotificationCompat.Builder(this)
            .setAutoCancel(true)
            .setContentIntent(
                PendingIntent.getActivity(
                    this, 0, mainIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT
                )
            )
            .setContentTitle(resources.getString(R.string.app_name))
            .setContentText("Пора пройти пару тестов:)")
            .setDefaults(Notification.DEFAULT_ALL)
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setTicker("ticker message")
            .setWhen(System.currentTimeMillis())
            .build()

        notificationManager.notify(0, noti)

        Log.i(TAG, "Notification created")

        when {
            MySharedPreferences.getBoolPreference(applicationContext, AppConstants.PREF_QUIZ_MONTH) ->
                MySharedPreferences.setPreference(applicationContext, AppConstants.PREF_QUIZ_MONTH, false)

            MySharedPreferences.getBoolPreference(applicationContext, AppConstants.PREF_QUIZ_HALF_YEAR) ->
                MySharedPreferences.setPreference(applicationContext, AppConstants.PREF_QUIZ_HALF_YEAR, false)

            MySharedPreferences.getBoolPreference(applicationContext, AppConstants.PREF_QUIZ_YEAR) ->
                MySharedPreferences.setPreference(applicationContext, AppConstants.PREF_QUIZ_YEAR, false)
        }
    }

    override fun onBind(intent: Intent): IBinder? {
        // TODO Auto-generated method stub
        return null
    }

    companion object {

        private val TAG = "ShowQuizNotification"
    }
}