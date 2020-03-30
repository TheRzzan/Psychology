package com.morozov.psychology.ui.fragments.deep.mind.fragments

import android.content.res.ColorStateList
import android.graphics.Typeface
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import com.morozov.psychology.R
import com.morozov.psychology.mvp.presenters.MainPresenter
import kotlinx.android.synthetic.main.fragment_deep_select.*

class DeepSelectFragment: Fragment() {

    lateinit var mActivityPresenter: MainPresenter

    lateinit var thinkList: List<String>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_deep_select, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        for (s in thinkList) {
            radioGroupThink.addThink(s)
        }

        radioGroupThink.setOnCheckedChangeListener { p0, id ->
            buttonSelectContr.setBackgroundResource(R.drawable.rectangle_button)
            buttonSelectContr.isEnabled = true
        }
    }

    private fun RadioGroup.addThink(text: String) {
        val radioButton = RadioButton(context)
        radioButton.text = text
        val params = RadioGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT, 6f)
        params.setMargins(0, 0, 0, 52)
        radioButton.buttonTintList = ColorStateList(arrayOf(intArrayOf(android.R.attr.state_enabled)), intArrayOf(resources.getColor(R.color.colorPrimary)))
        radioButton.layoutParams = params
        radioButton.setTextSize(TypedValue.COMPLEX_UNIT_PX, resources.getDimension(R.dimen.text_secondary_size))
        radioButton.setTextColor(resources.getColor(R.color.second_header_text))
        radioButton.setTypeface(null, Typeface.BOLD)
        addView(radioButton)
    }
}