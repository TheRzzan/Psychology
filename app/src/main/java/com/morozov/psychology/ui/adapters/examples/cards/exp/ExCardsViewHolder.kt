package com.morozov.psychology.ui.adapters.examples.cards.exp

import android.support.v4.view.ViewCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import com.morozov.psychology.ui.adapters.listeners.OnImageClickListener
import com.morozov.psychology.ui.adapters.listeners.OnItemClickListener
import com.morozov.psychology.utility.AppConstants
import com.morozov.psychology.utility.ExpImagesLoader
import kotlinx.android.synthetic.main.item_example_card.view.*

class ExCardsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun populate(name: String, pos: Int, listener: OnImageClickListener) {
        itemView.imageCard.setImageDrawable(ExpImagesLoader.getImageDescription(itemView.context, pos + 1))
        itemView.textExampleName.text = name
        itemView.imageCard.setOnClickListener{
            listener.onImageClicked(itemView.imageCard, pos)
        }
        if (pos < 5)
            ViewCompat.setTransitionName(itemView.imageCard, pos.toString() + "exp_image")
        else
            ViewCompat.setTransitionName(itemView.imageCard, pos.toString() + "fix_image")
    }
}
