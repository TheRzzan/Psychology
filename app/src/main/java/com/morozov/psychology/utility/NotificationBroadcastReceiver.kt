package com.morozov.psychology.utility

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import android.util.Log
import com.morozov.psychology.R
import com.morozov.psychology.ui.activities.MainActivity
import java.text.DateFormat
import java.util.*

class NotificationBroadcastReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val mainIntent = Intent(context, MainActivity::class.java)

        val notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channelId = "Your_channel_id"
        val GROUP_KEY_WORK_EMAIL = "com.morozov.psychology.WORK_EMAIL"
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

        val noti = NotificationCompat.Builder(context, channelId)
            .setPriority(Notification.PRIORITY_HIGH)
            .setVibrate(longArrayOf(0))
            .setAutoCancel(true)
            .setContentIntent(
                PendingIntent.getActivity(
                    context, 0, mainIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT
                )
            )
            .setContentTitle(context.resources.getString(R.string.app_name))
            .setContentText("Не забывайте проходить тесты для большего понимания себя и изменения своих мыслей")
            .setDefaults(Notification.DEFAULT_ALL)
            .setSmallIcon(R.drawable.ic_psychology_launcher)
            .setTicker("ticker message")
            .setWhen(System.currentTimeMillis())
            .setGroup(GROUP_KEY_WORK_EMAIL)
            .build()

        notificationManager.notify(0, noti)

        Log.i(TAG, "Notification created")

        if (MySharedPreferences.getBoolPreference(context, AppConstants.PREF_QUIZ_MONTH)) {
            if (!MySharedPreferences.getBoolPreference(context, AppConstants.PREF_QUIZ_HALF_YEAR)) {
                MySharedPreferences.setPreference(context, AppConstants.PREF_QUIZ_HALF_YEAR, true)

                val calendar = Calendar.getInstance()
                calendar.timeInMillis = System.currentTimeMillis()
                calendar.add(Calendar.MONTH, 1)
                setNotification(calendar, context)
            } else if (!MySharedPreferences.getBoolPreference(context, AppConstants.PREF_QUIZ_YEAR)) {
                MySharedPreferences.setPreference(context, AppConstants.PREF_QUIZ_YEAR, true)

                val calendar = Calendar.getInstance()
                calendar.timeInMillis = System.currentTimeMillis()
                calendar.add(Calendar.YEAR, 1)
                setNotification(calendar, context)
            } else {
                MySharedPreferences.setPreference(context, AppConstants.PREF_QUIZ_MONTH, false)
                MySharedPreferences.setPreference(context, AppConstants.PREF_QUIZ_HALF_YEAR, false)
                MySharedPreferences.setPreference(context, AppConstants.PREF_QUIZ_YEAR, false)
            }
        }
    }

    private fun setNotification(time: Calendar, context: Context) {
        val notificationIntent = Intent(context, NotificationBroadcastReceiver::class.java)
        val contentIntent = PendingIntent.getBroadcast(
            context, 0, notificationIntent,
            PendingIntent.FLAG_CANCEL_CURRENT
        )

        val am = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        am.cancel(contentIntent)
        am.setExact(AlarmManager.RTC_WAKEUP, time.timeInMillis , contentIntent)

        Log.i(TAG, "Notification created, info: ${DateFormat.getInstance().format(time.time)}")
    }

    companion object {
        val TAG = "NotfBReceiver"
    }
}