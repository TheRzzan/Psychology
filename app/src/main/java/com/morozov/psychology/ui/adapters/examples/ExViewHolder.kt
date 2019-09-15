package com.morozov.psychology.ui.adapters.examples

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_example_card.view.*

class ExViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun populate(name: String) {
        itemView.textExampleName.text = name
    }
}