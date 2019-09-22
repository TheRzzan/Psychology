package com.morozov.psychology.ui.adapters.diary.think.list

import android.support.v7.widget.RecyclerView
import android.view.View
import com.morozov.psychology.ui.adapters.listeners.OnItemClickListener
import kotlinx.android.synthetic.main.item_diary_think_card.view.*

class DiaryThinkViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun populate(element: Pair<String, String>, position: Int, listener: OnItemClickListener) {
        itemView.textDiarySituation.text = element.second
        itemView.textDiaryTime.text = element.first

        itemView.setOnClickListener {
            listener.onItemClick(itemView, position)
        }
    }
}