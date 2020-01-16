package com.morozov.psychology.ui.adapters.diary.date

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import com.morozov.psychology.R
import com.morozov.psychology.ui.adapters.listeners.OnItemClickListener
import com.morozov.psychology.utility.DateConverter
import kotlinx.android.synthetic.main.item_diary_date_card.view.*
import java.text.SimpleDateFormat
import java.util.*

class DiaryDateViewHolder(itemView: View): androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {

    @SuppressLint("RestrictedApi")
    fun populate(element: Date, position: Int, listener: OnItemClickListener) {
        val dayFormat = SimpleDateFormat("dd")
        val monthFormat = SimpleDateFormat("MM")
        val yearFormat = SimpleDateFormat("yyyy")
        itemView.textDiaryDay.text = dayFormat.format(element)
        itemView.textDiaryMonth.text = DateConverter.getStringMonthSimple(monthFormat.format(element))
        itemView.textDiaryYear.text = yearFormat.format(element)
        itemView.setOnClickListener{
            listener.onItemClick(itemView.diaryDateCard, position)
        }

        when (element.day) {
            0 -> itemView.imageDiaryCircle.setSupportBackgroundTintList(ContextCompat.getColorStateList(itemView.context, R.color.sunday))
            1 -> itemView.imageDiaryCircle.setSupportBackgroundTintList(ContextCompat.getColorStateList(itemView.context, R.color.monday))
            2 -> itemView.imageDiaryCircle.setSupportBackgroundTintList(ContextCompat.getColorStateList(itemView.context, R.color.tuesday))
            3 -> itemView.imageDiaryCircle.setSupportBackgroundTintList(ContextCompat.getColorStateList(itemView.context, R.color.wednesday))
            4 -> itemView.imageDiaryCircle.setSupportBackgroundTintList(ContextCompat.getColorStateList(itemView.context, R.color.thursday))
            5 -> itemView.imageDiaryCircle.setSupportBackgroundTintList(ContextCompat.getColorStateList(itemView.context, R.color.friday))
            6 -> itemView.imageDiaryCircle.setSupportBackgroundTintList(ContextCompat.getColorStateList(itemView.context, R.color.saturday))
        }
    }
}