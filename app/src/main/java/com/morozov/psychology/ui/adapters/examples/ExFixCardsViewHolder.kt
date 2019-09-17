package com.morozov.psychology.ui.adapters.examples

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_example_fixing_card.view.*

class ExFixCardsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun populate(name: String, listener: View.OnClickListener) {
        itemView.textExampleFixingName.text = name
        itemView.imageCardFixing.setOnClickListener(listener)
    }
}