package com.morozov.psychology.ui.adapters.diary.think.list

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_diary_think_card.view.*

class DiaryThinkViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun populate(element: Pair<String, String>) {
        itemView.textDiarySituation.text = element.first
        itemView.textDiaryTime.text = element.second
    }
}