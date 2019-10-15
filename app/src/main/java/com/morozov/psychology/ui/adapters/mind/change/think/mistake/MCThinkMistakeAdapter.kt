package com.morozov.psychology.ui.adapters.mind.change.think.mistake

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import com.morozov.psychology.R
import com.morozov.psychology.ui.adapters.ListAdapter

class MCThinkMistakeAdapter: ListAdapter<Pair<Drawable, Pair<String, String>>, MCThinkMistakeViewHolder>() {

    override fun onCreateViewHolder(container: ViewGroup, p1: Int): MCThinkMistakeViewHolder =
        MCThinkMistakeViewHolder(
            LayoutInflater.from(container.context).inflate(
                R.layout.item_mind_change_thinking_mistake,
                container,
                false
            )
        )

    override fun onBindViewHolder(holder: MCThinkMistakeViewHolder, position: Int) {
        holder.populate(data()[position])
    }
}