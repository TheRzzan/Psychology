package com.morozov.psychology.ui.adapters.diary.think.list

import android.support.v7.widget.RecyclerView
import android.view.View
import com.morozov.psychology.mvp.models.diary.ThinkModel
import com.morozov.psychology.ui.adapters.listeners.OnItemClickListener
import kotlinx.android.synthetic.main.item_diary_think_card.view.*
import java.text.SimpleDateFormat

class DiaryThinkViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun populate(element: ThinkModel, position: Int, listener: OnItemClickListener) {
        val timeFormat = SimpleDateFormat("HH:mm")

        itemView.textDiarySituation.text = element.situation
        itemView.textDiaryTime.text = timeFormat.format(element.date)

        itemView.setOnClickListener {
            listener.onItemClick(itemView, position)
        }

//        if (element.isOverwrited)
//            itemView.imageIsOverwrited.visibility = View.VISIBLE
//        else
//            itemView.imageIsOverwrited.visibility = View.GONE
    }
}