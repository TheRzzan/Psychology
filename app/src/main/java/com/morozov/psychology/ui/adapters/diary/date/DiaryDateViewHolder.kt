package com.morozov.psychology.ui.adapters.diary.date

import android.support.v7.widget.RecyclerView
import android.view.View
import com.morozov.psychology.utility.DateConverter
import kotlinx.android.synthetic.main.item_diary_date_card.view.*
import java.text.SimpleDateFormat
import java.util.*

class DiaryDateViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun populate(element: Date, listener: View.OnClickListener) {
        val dayFormat = SimpleDateFormat("dd")
        val monthFormat = SimpleDateFormat("MM")
        val yearFormat = SimpleDateFormat("yyyy")
        itemView.textDiaryDay.text = dayFormat.format(element)
        itemView.textDiaryMonth.text = DateConverter.getStringMonthSimple(monthFormat.format(element))
        itemView.textDiaryYear.text = yearFormat.format(element)
        itemView.setOnClickListener(listener)
    }
}