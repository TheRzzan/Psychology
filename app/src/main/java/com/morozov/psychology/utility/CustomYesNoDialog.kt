package com.morozov.psychology.utility

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.morozov.psychology.R
import kotlinx.android.synthetic.main.dialog_yes_no_layout.*

class CustomYesNoDialog: DialogFragment() {

    companion object {
        fun showDialog(question: String, ok: String, cancel: String, rY: Runnable, rN: Runnable,
                       supportFragmentManager: FragmentManager) {
            val customDialog = CustomYesNoDialog()
            customDialog.question = question
            customDialog.ok = ok
            customDialog.cancel = cancel
            customDialog.runY = rY
            customDialog.runN = rN
            customDialog.show(supportFragmentManager, CustomYesNoDialog::class.simpleName)
        }
    }

    lateinit var question: String

    var ok = "Да"
    var cancel = "Отмена"

    lateinit var runY: Runnable
    lateinit var runN: Runnable

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_yes_no_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textYNQuestion.text = question

        textDialogOK.text = ok
        textDialogCancel.text = cancel

        textDialogOK.setOnClickListener {
            runY.run()
            dialog.dismiss()
        }

        textDialogCancel.setOnClickListener {
            runN.run()
            dialog.dismiss()
        }

        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}