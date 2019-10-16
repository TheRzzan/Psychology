package com.morozov.psychology.ui.adapters.mind.change.aback.white

import android.support.v7.widget.RecyclerView
import android.text.TextWatcher
import android.view.View
import kotlinx.android.synthetic.main.item_example_test.view.*

class MCBlackWhiteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun populate(str: String, textWatcher: TextWatcher) {
        itemView.textTestQuestion.visibility = View.GONE
        itemView.editTextAnswer.hint = str
        itemView.editTextAnswer.addTextChangedListener(textWatcher)
    }
}