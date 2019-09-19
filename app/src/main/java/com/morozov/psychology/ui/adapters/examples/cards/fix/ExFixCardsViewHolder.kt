package com.morozov.psychology.ui.adapters.examples.cards.fix

import android.support.v7.widget.RecyclerView
import android.view.View
import com.morozov.psychology.ui.adapters.listeners.OnItemClickListener
import kotlinx.android.synthetic.main.item_example_card.view.*
import kotlinx.android.synthetic.main.item_example_fixing_card.view.*

class ExFixCardsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun populate(name: String, pos: Int, listener: OnItemClickListener) {
        itemView.textExampleFixingName.text = name
        itemView.imageCardFixing.setOnClickListener{
            listener.onItemClick(itemView.imageCardFixing, pos)
        }
    }
}