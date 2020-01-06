package com.morozov.psychology.ui.adapters.mind.change.edit.seekbar

import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.SeekBar
import com.morozov.psychology.ui.adapters.listeners.OnTextChangeListener
import kotlinx.android.synthetic.main.item_homework_edit_seek.view.*

class EditSeekViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun populate(hideSeek: Boolean, text: String, hint: String,
                 position: Int, listener: OnTextChangeListener,
                 savedText: Pair<String, Int>? = null) {
        itemView.textHomItem.text = text
        itemView.editHomItem.hint = hint

        itemView.editHomItem.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != null) {
                    listener.onTextChanged(position, s.length, s.toString())
                } else {
                    listener.onTextChanged(position, 0, s.toString())
                }
            }
        })

        itemView.seekBarHome1.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                itemView.textHomItemPercent1.text = "$progress%"
                listener.onTextChanged(position, itemView.editHomItem.text.length,
                    itemView.editHomItem.text.toString(), progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

        if (hideSeek) {
            itemView.seekBarHome1.visibility = View.GONE
            itemView.textHomItemPercent1.visibility = View.GONE
        }

        if (savedText != null) {
            itemView.editHomItem.setText(savedText.first)
            itemView.seekBarHome1.progress = savedText.second
        }
    }
}