package com.morozov.psychology.utility

import android.app.Activity
import android.content.Context
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.morozov.psychology.ui.activities.MainActivity

fun hideKeyboard(activity: Activity) {
    val inputMethodManager =
        activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?
    inputMethodManager!!.hideSoftInputFromWindow(activity.currentFocus?.windowToken, 0)
}