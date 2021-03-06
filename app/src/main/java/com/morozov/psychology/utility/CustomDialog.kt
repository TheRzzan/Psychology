package com.morozov.psychology.utility

import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.morozov.psychology.R
import kotlinx.android.synthetic.main.dialog_layout.*

class CustomDialog: DialogFragment() {

    private lateinit var title: String
    private lateinit var description: String
    private var ok = "OK"
    private var run: Runnable? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textDialogHeader.text = title
        textDialogDescription.text = description
        textDialogOK.text = ok

        textDialogOK.setOnClickListener {
            run?.run()
            dialog.dismiss()
        }

        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        if (run != null)
            dialog.window.setDimAmount(0f)
    }

    fun setTitle(title: String) {
        this.title = title
    }

    fun setDescription(description: String) {
        this.description = description
    }

    fun setOk(text: String, runnable: Runnable? = null) {
        ok = text
        run = runnable
    }

    override fun onDismiss(dialog: DialogInterface?) {
        run?.run()
        super.onDismiss(dialog)
    }
}