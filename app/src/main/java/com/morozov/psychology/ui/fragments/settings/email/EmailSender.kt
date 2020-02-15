package com.morozov.psychology.ui.fragments.settings.email

import android.app.Activity
import android.content.Intent

object EmailSender {
    var recepient = "psychotherapevt@yandex.ru"
    var subject = "Запись на консультацию"
    var text: String? = null

    fun send(activity: Activity) {
        if (recepient == null || text == null)
            return

        val  emailIntent = Intent(Intent.ACTION_SEND)
        emailIntent.type = "plain/text"
        emailIntent.putExtra(Intent.EXTRA_EMAIL, recepient)
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
        emailIntent.putExtra(Intent.EXTRA_TEXT, text)

        activity.startActivity(Intent.createChooser(emailIntent,
            "Отправка письма..."))
    }
}