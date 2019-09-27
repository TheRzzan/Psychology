package com.morozov.psychology.ui.adapters.diary.think.list

import android.support.v7.widget.RecyclerView
import android.view.View
import com.morozov.psychology.mvp.models.diary.ThinkModel
import com.morozov.psychology.ui.adapters.listeners.OnItemClickListener
import kotlinx.android.synthetic.main.item_diary_think_card.view.*
import java.text.SimpleDateFormat

class DiaryThinkViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    companion object {
        private const val MAX_SYMBOLS_SITUATION = 16
        private const val MAX_SYMBOLS_THINK = 32
    }

    fun populate(element: ThinkModel, position: Int, listener: OnItemClickListener) {
        val timeFormat = SimpleDateFormat("HH:mm")

        var situation = element.situation
        var think = element.think

        if (situation.length > MAX_SYMBOLS_SITUATION) {
            situation = situation.substring(IntRange(0, MAX_SYMBOLS_SITUATION - 1)) + "..."
        }

        if (think.length > MAX_SYMBOLS_THINK) {
            think = think.substring(IntRange(0, MAX_SYMBOLS_THINK - 1)) + "..."
        }

        itemView.textDiarySituation.text = situation
        itemView.textDiaryThink.text = think
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