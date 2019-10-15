package com.morozov.psychology.ui.adapters.mind.change.think.mistake

import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_mind_change_thinking_mistake.view.*

class MCThinkMistakeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun populate(element: Pair<Drawable, Pair<String, String>>) {
        itemView.imageThinkMistake.setImageDrawable(element.first)
        val pair = element.second
        itemView.textThinkMistakeDescr.text = pair.first
        itemView.textThinkMistakeExmpl.text = pair.second
    }
}