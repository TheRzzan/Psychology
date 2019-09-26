package com.morozov.psychology.ui.adapters.diary.date

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_diary_date_card.view.*

class DiaryDateViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun populate(element: Pair<Int, String>, listener: View.OnClickListener) {
        itemView.textDiaryDay.text = element.first.toString()
        itemView.textDiaryMonth.text = element.second
        itemView.setOnClickListener(listener)
    }
}