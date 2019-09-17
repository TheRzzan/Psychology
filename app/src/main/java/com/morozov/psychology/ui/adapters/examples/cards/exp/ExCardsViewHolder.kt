package com.morozov.psychology.ui.adapters.examples.cards.exp

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_example_card.view.*

class ExCardsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun populate(name: String, listener: View.OnClickListener) {
        itemView.textExampleName.text = name
        itemView.imageCard.setOnClickListener(listener)
    }
}
