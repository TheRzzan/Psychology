package com.morozov.psychology.ui.adapters.diary.think.list

import com.google.android.material.snackbar.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.morozov.psychology.R
import com.morozov.psychology.mvp.models.diary.ThinkModel
import com.morozov.psychology.mvp.presenters.diary.DiaryMainPresenter
import com.morozov.psychology.mvp.presenters.diary.DiaryPresenter
import com.morozov.psychology.ui.adapters.ListAdapter
import com.morozov.psychology.ui.adapters.listeners.OnItemClickListener
import com.morozov.psychology.utility.CustomYesNoDialog
import com.morozov.psychology.utility.ItemTouchHelperClass

class DiaryMainThinkAdapter(private val listener: OnItemClickListener,
                            private val mPresenter: DiaryMainPresenter,
                            private val view: View,
                            private val manager: FragmentManager)
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
        notifyDataSetChanged()

        CustomYesNoDialog.showDialog("Вы точно хотите удалить?",
            "Удалить", "Отмена",
            Runnable {},
            Runnable {
                mPresenter.addThink(indexOfDeletedItem, justDeletedItem)
                notifyDataSetChanged()
            }, manager)

//        Snackbar.make(view, "Удалено", Snackbar.LENGTH_LONG)
//            .setAction("Отмена") {
//                mPresenter.addThink(indexOfDeletedItem, justDeletedItem)
//                notifyDataSetChanged()
//            }.show()
    }
}