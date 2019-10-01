package com.morozov.psychology.ui.adapters.tests.results

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.animation.AnimationUtils
import com.morozov.psychology.R
import kotlinx.android.synthetic.main.item_tests_quiz_result.view.*

class TstResultsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun populate(mark: String, description: String) {
        itemView.textQuizResultMark.text = mark
        itemView.textQuizResultDescription.text = description

        val animRotRight = AnimationUtils.loadAnimation(itemView.context, R.anim.rotate_center_right)
        animRotRight.fillAfter = true
        animRotRight.isFillEnabled = true

        val animRotLeft = AnimationUtils.loadAnimation(itemView.context, R.anim.rotate_center_left)
        animRotLeft.fillAfter = true
        animRotLeft.isFillEnabled = true

        var b = true
        itemView.imageArrowOpening.setOnClickListener{
            when (b) {
                true -> {
                    itemView.textQuizResultDescription.visibility = View.VISIBLE
                    itemView.imageArrowOpening.startAnimation(animRotRight)
                }
                false -> {
                    itemView.textQuizResultDescription.visibility = View.GONE
                    itemView.imageArrowOpening.startAnimation(animRotLeft)
                }
            }

            b = !b
        }
    }
}