package com.morozov.psychology.ui.adapters.mind.change.edit.seekbar

import android.view.LayoutInflater
import android.view.ViewGroup
import com.morozov.psychology.R
import com.morozov.psychology.ui.adapters.ListAdapter
import com.morozov.psychology.ui.adapters.listeners.OnTextChangeListener

class EditSeekAdapter(private val listener: OnTextChangeListener, private val hideSeek: Boolean = false)
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
        holder.populate(hideSeek, data()[position].first, data()[position].second, position, listener)
    }
}