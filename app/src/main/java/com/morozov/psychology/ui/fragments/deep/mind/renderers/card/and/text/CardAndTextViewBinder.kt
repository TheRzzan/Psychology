package com.morozov.psychology.ui.fragments.deep.mind.renderers.card.and.text

import com.github.vivchar.rendererrecyclerviewadapter.binder.ViewBinder
import com.github.vivchar.rendererrecyclerviewadapter.binder.ViewFinder
import com.morozov.psychology.R

class CardAndTextViewBinder(private val listener: OnCardClickListener): ViewBinder.Binder<CardAndTextModel> {
    override fun bindView(model: CardAndTextModel, finder: ViewFinder, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) {
            finder.setText(R.id.textRend, model.text)
            finder.setText(R.id.textRendPercent, "${model.percent}%")
            finder.setOnClickListener(R.id.cardRend) {
                listener.onClick(model.position)
            }
        }
    }
}