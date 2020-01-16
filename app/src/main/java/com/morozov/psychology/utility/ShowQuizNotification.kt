package com.morozov.psychology.utility

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import android.util.Log
import com.morozov.psychology.ui.activities.MainActivity
import java.text.DateFormat
import java.util.*
import android.app.NotificationChannel
import android.os.Build
import android.app.NotificationManager
import android.graphics.Color
import com.morozov.psychology.R

class ShowQuizNotification : Service() {

    override fun onCreate() {
        super.onCreate()

        val mainIntent = Intent(this, MainActivity::class.java)

        val notificationManager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channelId = "Your_channel_id"
        // === Removed some obsoletes
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId, "My channel",
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.description = "My channel description"
            channel.enableLights(true)
            channel.lightColor = Color.RED
            channel.enableVibration(false)
            notificationManager.createNotificationChannel(channel)
        }

        val noti = NotificationCompat.Builder(this, channelId)
            .setPriority(Notification.PRIORITY_HIGH)
            .setVibrate(longArrayOf(0))
            .setAutoCancel(true)
            .setContentIntent(
                PendingIntent.getActivity(
                    this, 0, mainIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT
                )
            )
            .setContentTitle(resources.getString(R.string.app_name))
            .setContentText("Не забывайте проходить тесты для большего понимания себя и изменения своих мыслей")
            .setDefaults(Notification.DEFAULT_ALL)
            .setSmallIcon(R.drawable.ic_psychology_launcher)
            .setTicker("ticker message")
            .setWhen(System.currentTimeMillis())
            .build()

        notificationManager.notify(0, noti)

        Log.i(TAG, "Notification created")

        if (MySharedPreferences.getBoolPreference(applicationContext, AppConstants.PREF_QUIZ_MONTH)) {
            if (!MySharedPreferences.getBoolPreference(applicationContext, AppConstants.PREF_QUIZ_HALF_YEAR)) {
                MySharedPreferences.setPreference(applicationContext, AppConstants.PREF_QUIZ_HALF_YEAR, true)

                val calendar = Calendar.getInstance()
                calendar.timeInMillis = System.currentTimeMillis()
                calendar.add(Calendar.MINUTE, 10)
                setNotification(calendar)
            } else if (!MySharedPreferences.getBoolPreference(applicationContext, AppConstants.PREF_QUIZ_YEAR)) {
                MySharedPreferences.setPreference(applicationContext, AppConstants.PREF_QUIZ_YEAR, true)

                val calendar = Calendar.getInstance()
                calendar.timeInMillis = System.currentTimeMillis()
                calendar.add(Calendar.MINUTE, 15)
                setNotification(calendar)
            } else {
                MySharedPreferences.setPreference(applicationContext, AppConstants.PREF_QUIZ_MONTH, false)
                MySharedPreferences.setPreference(applicationContext, AppConstants.PREF_QUIZ_HALF_YEAR, false)
                MySharedPreferences.setPreference(applicationContext, AppConstants.PREF_QUIZ_YEAR, false)
            }
        }
    }

    private fun setNotification(time: Calendar) {
        val notificationIntent = Intent(applicationContext, ShowQuizNotification::class.java)
        val contentIntent = PendingIntent.getService(
            applicationContext, 0, notificationIntent,
            PendingIntent.FLAG_CANCEL_CURRENT
        )

        val am = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        am.cancel(contentIntent)
        am.setExact(AlarmManager.RTC_WAKEUP, time.timeInMillis , contentIntent)

        Log.i("MainTag", "Notification created, info: ${DateFormat.getInstance().format(time.time)}")
    }

    override fun onBind(intent: Intent): IBinder? {
        // TODO Auto-generated method stub
        return null
    }

    companion object {

        private val TAG = "ShowQuizNotification"
    }
}