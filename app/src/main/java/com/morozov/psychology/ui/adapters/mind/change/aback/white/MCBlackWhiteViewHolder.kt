package com.morozov.psychology.ui.adapters.mind.change.aback.white

import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import kotlinx.android.synthetic.main.item_example_test.view.*

class MCBlackWhiteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun populate(str: String, adapter: MCBlackWhiteAdapter) {
        itemView.textTestQuestion.visibility = View.GONE
        itemView.editTextAnswer.hint = str
        itemView.editTextAnswer.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (before == 0 && count > 0)
                    adapter.filledCount++
                else if (count <= 0)
                    adapter.filledCount--

                when {
                    adapter.filledCount < adapter.itemCount -> adapter.isAllFilled.value = false
                    else -> adapter.isAllFilled.value = true
                }
            }
        })
    }
}