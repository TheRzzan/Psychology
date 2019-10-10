package com.morozov.psychology.ui.adapters.diary.date

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.morozov.psychology.R
import com.morozov.psychology.ui.adapters.ListAdapter
import com.morozov.psychology.ui.adapters.listeners.OnItemClickListener
import org.joda.time.DateTime
import org.joda.time.Days
import org.joda.time.MutableDateTime
import java.util.*

class DiaryMainDateAdapter(private val listener: OnItemClickListener): ListAdapter<Date, DiaryDateViewHolder>() {

    override fun onCreateViewHolder(container: ViewGroup, p1: Int): DiaryDateViewHolder =
        DiaryDateViewHolder(
            LayoutInflater.from(container.context).inflate(
                R.layout.item_diary_date_card,
                container,
                false
            )
        )

    override fun onBindViewHolder(holder: DiaryDateViewHolder, position: Int) {
        val calendar = Calendar.getInstance()
        calendar.time = Date(0)
        calendar.add(Calendar.DATE, position)

        val date = calendar.time

        holder.populate(date, position, listener)
    }

    override fun getItemCount(): Int {
        val epoch = MutableDateTime()
        epoch.setDate(0)
        val now = DateTime()

        return Days.daysBetween(epoch, now).days + 1
    }
}