package com.morozov.psychology.ui.adapters.diary.think.list

import android.view.LayoutInflater
import android.view.ViewGroup
import com.morozov.psychology.R
import com.morozov.psychology.ui.adapters.ListAdapter
import com.morozov.psychology.ui.adapters.listeners.OnItemClickListener

class DiaryThinkAdapter(private val listener: OnItemClickListener): ListAdapter<Pair<String, String>, DiaryThinkViewHolder>() {

    override fun onCreateViewHolder(container: ViewGroup, p1: Int): DiaryThinkViewHolder =
        DiaryThinkViewHolder(
            LayoutInflater.from(container.context).inflate(
                R.layout.item_diary_think_card,
                container,
                false
            )
        )

    override fun onBindViewHolder(holder: DiaryThinkViewHolder, position: Int) {
        holder.populate(data()[position], position, listener)
    }
}