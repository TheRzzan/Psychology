package com.morozov.psychology.ui.adapters.examples.results

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import com.morozov.psychology.mvp.models.examples.ExFixingResultModel
import kotlinx.android.synthetic.main.item_example_fixing_result.view.*

class ExFixResultViewHolder(itemView: View): androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {

    fun populate(model: ExFixingResultModel) {
        itemView.textResult.text = model.name
        itemView.textResultUser.text = model.userText
        itemView.textResultTrue.text = model.trueText
    }
}