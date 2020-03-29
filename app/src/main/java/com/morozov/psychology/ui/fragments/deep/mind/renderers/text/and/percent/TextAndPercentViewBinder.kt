package com.morozov.psychology.ui.fragments.deep.mind.renderers.text.and.percent

import com.github.vivchar.rendererrecyclerviewadapter.binder.ViewBinder
import com.github.vivchar.rendererrecyclerviewadapter.binder.ViewFinder
import com.morozov.psychology.R

class TextAndPercentViewBinder(private val listener: OnItemClickListener): ViewBinder.Binder<TextAndPercentModel> {
    override fun bindView(model: TextAndPercentModel, finder: ViewFinder, payloads: MutableList<Any>) {
        finder.setText(R.id.textRendThink, model.text)
        finder.setText(R.id.textRendPercent, "${model.percent}%")
        finder.setOnClickListener(R.id.rootRend) {
            listener.onClick(model.position)
        }
    }
}