package com.morozov.psychology.ui.fragments.deep.mind.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.morozov.psychology.R
import java.lang.Exception

class DeepMindTestFragment: Fragment() {

    private val listOfThinks = mutableListOf<String>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_deep_mind_test, container, false)

    private fun LinearLayout.addTextView(text: String) {
        val textView = TextView(context)
        textView.setPadding(16, 0, 16, 16)
        textView.textSize = resources.getDimension(R.dimen.text_secondary_size)
        textView.setTextColor(resources.getColor(R.color.second_header_text))
        textView.text = text
        this.addView(textView)
    }

    private fun LinearLayout.addEditText(hint: String, position: Int) {
        val editText = EditText(context)
        editText.setPadding(16, 8, 16, 24)
        editText.hint = hint
        editText.setTextColor(resources.getColor(R.color.second_header_text))
        try {
            val f = TextView::class.java.getDeclaredField("mCursorDrawableRes")
            f.isAccessible = true
            f.set(editText, R.drawable.cursor_color)
        } catch (ignore: Exception) {}
        editText.background = resources.getDrawable(R.drawable.rectangle_long_edit_text_with_shadow)
        editText.addTextChangedListener(object: MyTextWatcher(){
            override fun getPosition(): Int = position
        })
    }

    private abstract inner class MyTextWatcher: TextWatcher{
        override fun afterTextChanged(p0: Editable?) {}
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            // Check is ready
        }
        abstract fun getPosition(): Int
    }
}