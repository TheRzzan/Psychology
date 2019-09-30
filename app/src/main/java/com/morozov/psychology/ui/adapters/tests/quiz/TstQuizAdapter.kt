package com.morozov.psychology.ui.adapters.tests.quiz

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.morozov.psychology.R
import com.morozov.psychology.ui.adapters.ListAdapter
import com.morozov.psychology.ui.adapters.listeners.OnItemClickListener

class TstQuizAdapter: ListAdapter<String, TstQuizViewHolder>(), OnItemClickListener {

    var selectedPosition = -1

    override fun onCreateViewHolder(container: ViewGroup, p1: Int): TstQuizViewHolder =
        TstQuizViewHolder(
            LayoutInflater.from(container.context).inflate(
                R.layout.item_tests_quiz,
                container,
                false
            )
        )

    override fun onBindViewHolder(holder: TstQuizViewHolder, position: Int) {
        holder.populate(data()[position], position, position == selectedPosition, this)
    }

    /*
    * OnItemClickListener implementation
    *
    * */
    override fun onItemClick(view: View, position: Int) {
        selectedPosition = position
        notifyDataSetChanged()
    }
}