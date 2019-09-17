package com.morozov.psychology.ui.adapters.examples.test

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_example_test.view.*

class ExTestViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun populate(text: String) {
        itemView.textTestQuestion.text = text
    }
}