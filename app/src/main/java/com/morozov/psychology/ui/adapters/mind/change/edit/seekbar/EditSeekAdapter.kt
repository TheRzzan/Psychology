package com.morozov.psychology.ui.adapters.mind.change.edit.seekbar

import android.view.LayoutInflater
import android.view.ViewGroup
import com.morozov.psychology.R
import com.morozov.psychology.ui.adapters.ListAdapter
import com.morozov.psychology.ui.adapters.listeners.OnTextChangeListener

class EditSeekAdapter(private val listener: OnTextChangeListener, private val hideSeek: Boolean = false,
                      private val savedDis: Pair<String, String>? = null)
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
        var savedText: String? = null

        if (savedDis!= null) {
            when(position) {
                0 -> savedText = savedDis.second
                1 -> savedText = savedDis.first
            }
        }

        holder.populate(hideSeek, data()[position].first, data()[position].second, position, listener, savedText)
    }
}