package com.morozov.psychology.ui.adapters.settings.styles

import android.content.res.ColorStateList
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import com.morozov.psychology.ui.adapters.listeners.OnItemClickListener
import kotlinx.android.synthetic.main.item_settings_style.view.*

class StgStyleViewHolder(itemView: View): androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {

    fun populate(pair: Pair<String, Int>, position: Int, b: Boolean, listener: OnItemClickListener) {
        itemView.radioButtonStyle.buttonTintList = ColorStateList(arrayOf(intArrayOf(android.R.attr.state_enabled)), intArrayOf(pair.second))
        itemView.radioButtonStyle.text = pair.first
        itemView.radioButtonStyle.isChecked = b
        itemView.radioButtonStyle.setOnClickListener{
            listener.onItemClick(itemView, position)
        }
    }
}