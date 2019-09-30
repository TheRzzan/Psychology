package com.morozov.psychology.ui.adapters.tests.quiz

import android.support.v7.widget.RecyclerView
import android.view.View
import com.morozov.psychology.ui.adapters.listeners.OnItemClickListener
import kotlinx.android.synthetic.main.item_tests_quiz.view.*

class TstQuizViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun populate(text: String, position: Int, b: Boolean, listener: OnItemClickListener) {
        itemView.radioButtonQuiz.text = text
        itemView.radioButtonQuiz.isSelected = b
        itemView.radioButtonQuiz.setOnClickListener{
            listener.onItemClick(itemView, position)
        }
    }
}