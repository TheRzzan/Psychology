package com.morozov.psychology.ui.adapters.tests.results

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_tests_quiz_result.view.*

class TstResultsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun populate(mark: String, description: String) {
        itemView.textQuizResultMark.text = mark
        itemView.textQuizResultDescription.text = description
    }
}