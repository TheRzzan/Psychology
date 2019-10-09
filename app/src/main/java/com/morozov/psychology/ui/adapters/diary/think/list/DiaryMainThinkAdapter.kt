package com.morozov.psychology.ui.adapters.diary.think.list

import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.morozov.psychology.R
import com.morozov.psychology.mvp.models.diary.ThinkModel
import com.morozov.psychology.mvp.presenters.diary.DiaryMainPresenter
import com.morozov.psychology.mvp.presenters.diary.DiaryPresenter
import com.morozov.psychology.ui.adapters.ListAdapter
import com.morozov.psychology.ui.adapters.listeners.OnItemClickListener
import com.morozov.psychology.utility.ItemTouchHelperClass

class DiaryMainThinkAdapter(private val listener: OnItemClickListener, private val mPresenter: DiaryMainPresenter, private val view: View)
    : ListAdapter<ThinkModel, DiaryThinkViewHolder>(),
    ItemTouchHelperClass.ItemTouchHelperAdapter{

    lateinit var justDeletedItem: ThinkModel
    var indexOfDeletedItem = -1

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

    /*
    * ItemTouchHelperAdapter implementation
    *
    * */
    override fun onItemRemoved(position: Int) {
        justDeletedItem = mPresenter.deleteThink(data()[position])!!
        indexOfDeletedItem = position
        notifyItemRemoved(position)
        notifyDataSetChanged()

        Snackbar.make(view, "Удалено", Snackbar.LENGTH_LONG)
            .setAction("Отмена") {
                mPresenter.addThink(indexOfDeletedItem, justDeletedItem)
                notifyItemInserted(indexOfDeletedItem)
                notifyDataSetChanged()
            }.show()
    }
}