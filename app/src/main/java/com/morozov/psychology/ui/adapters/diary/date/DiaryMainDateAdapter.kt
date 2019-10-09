package com.morozov.psychology.ui.adapters.diary.date

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.morozov.psychology.R
import com.morozov.psychology.ui.adapters.ListAdapter
import org.joda.time.DateTime
import org.joda.time.Days
import org.joda.time.MutableDateTime
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*

class DiaryMainDateAdapter: ListAdapter<Date, DiaryDateViewHolder>(), View.OnClickListener {

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

        holder.populate(date, this)
    }

    /*
    * OnClickListener implementation
    *
    * */
    override fun onClick(v: View?) {

    }

    override fun getItemCount(): Int {
        val epoch = MutableDateTime()
        epoch.setDate(0)
        val now = DateTime()

        return Days.daysBetween(epoch, now).days + 1
    }
}