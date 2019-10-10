package com.morozov.psychology.ui.adapters.diary.date

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.morozov.psychology.R
import com.morozov.psychology.ui.adapters.ListAdapter
import com.morozov.psychology.ui.adapters.listeners.OnItemClickListener
import java.util.*

class DiaryDateAdapter(val listener: OnItemClickListener): ListAdapter<Date, DiaryDateViewHolder>() {

    override fun onCreateViewHolder(container: ViewGroup, p1: Int): DiaryDateViewHolder =
        DiaryDateViewHolder(
            LayoutInflater.from(container.context).inflate(
                R.layout.item_diary_date_card,
                container,
                false
            )
        )

    override fun onBindViewHolder(holder: DiaryDateViewHolder, position: Int) {
        holder.populate(data()[position], position, listener)
    }
}