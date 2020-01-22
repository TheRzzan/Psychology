package com.morozov.psychology.ui.adapters.mind.change.edit.seekbar

import android.view.LayoutInflater
import android.view.ViewGroup
import com.morozov.psychology.R
import com.morozov.psychology.ui.adapters.ListAdapter
import com.morozov.psychology.ui.adapters.listeners.OnTextChangeListener
import com.morozov.psychology.utility.DisastorousPreferences

class EditSeekAdapter(private val listener: OnTextChangeListener, private val hideSeek: Boolean = false,
                      private val savedDis: Pair<Pair<String, Int>, Pair<String, Int>>? = null,
                      private val savedDis1: DisastorousPreferences.Dis1? = null)
    : ListAdapter<Pair<String, String>, EditSeekViewHolder>() {

    override fun onCreateViewHolder(container: ViewGroup, p1: Int): EditSeekViewHolder =
        EditSeekViewHolder(
            LayoutInflater.from(container.context).inflate(
                R.layout.item_homework_edit_seek,
                container,
                false
            )
        )

    override fun onBindViewHolder(holder: EditSeekViewHolder, position: Int) {
        var savedText: Pair<String, Int>? = null
        var dis1Str: String? = null

        if (savedDis!= null) {
            when(position) {
                0 -> savedText = savedDis.second
                1 -> savedText = savedDis.first
            }
        }

        if (savedDis1!= null) {
            when(position) {
                0 -> dis1Str = savedDis1.good
                1 -> dis1Str = savedDis1.think
                2 -> dis1Str = savedDis1.forecast
            }
        }

        holder.populate(hideSeek, data()[position].first, data()[position].second, position, listener, savedText, dis1Str)
    }
}