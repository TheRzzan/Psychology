package com.morozov.psychology.ui.adapters.examples.cards.exp

import android.support.v7.widget.RecyclerView
import android.view.View
import com.morozov.psychology.ui.adapters.listeners.OnItemClickListener
import kotlinx.android.synthetic.main.item_example_card.view.*

class ExCardsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun populate(name: String, pos: Int, listener: OnItemClickListener) {
        itemView.textExampleName.text = name
        itemView.imageCard.setOnClickListener{
            listener.onItemClick(itemView.imageCard, pos)
        }
    }
}
