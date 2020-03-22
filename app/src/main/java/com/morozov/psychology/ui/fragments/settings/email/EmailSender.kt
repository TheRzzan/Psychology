package com.morozov.psychology.ui.fragments.settings.email

import android.app.Activity
import android.content.Intent
import android.net.Uri
import de.cketti.mailto.EmailIntentBuilder

object EmailSender {
    var recepient = "psychotherapevt@yandex.ru"
    var subject = "Запись на консультацию"
    var text: String? = null

    fun send(activity: Activity) {
        if (text == null)
            return

        val emailIntent = EmailIntentBuilder.from(activity)
            .to(recepient)
            .subject(subject)
            .body(text!!)
            .build()
//        val  emailIntent = Intent(Intent.ACTION_SEND)
//        emailIntent.type = "message/rfc822"
//        emailIntent.putExtra(Intent.EXTRA_EMAIL, recepient)
//        emailIntent.setData(Uri.parse("mailto:$recepient"))
//        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
//        emailIntent.putExtra(Intent.EXTRA_TEXT, text)



        activity.startActivity(Intent.createChooser(emailIntent,
            "Отправка письма..."))
    }
}