package com.morozov.psychology.ui.adapters.mind.change

import com.google.android.material.snackbar.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.morozov.psychology.R
import com.morozov.psychology.mvp.models.diary.ThinkModel
import com.morozov.psychology.mvp.presenters.diary.DiaryMainPresenter
import com.morozov.psychology.mvp.presenters.diary.DiaryPresenter
import com.morozov.psychology.ui.adapters.ListAdapter
import com.morozov.psychology.ui.adapters.diary.think.list.DiaryThinkViewHolder
import com.morozov.psychology.ui.adapters.listeners.OnItemClickListener
import com.morozov.psychology.utility.ItemTouchHelperClass

class MindChangeThinkAdapter(private val listener: OnItemClickListener) : ListAdapter<ThinkModel, DiaryThinkViewHolder>(){

    override fun onCreateViewHolder(container: ViewGroup, p1: Int): DiaryThinkViewHolder =
        DiaryThinkViewHolder(
            LayoutInflater.from(container.context).inflate(
                R.layout.item_diary_think_card,
                container,
                false
            )
        )

    override fun onBindViewHolder(holder: DiaryThinkViewHolder, position: Int) {
        holder.populate(data()[position], position, listener)
    }
}