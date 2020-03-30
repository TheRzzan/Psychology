package com.morozov.psychology.ui.fragments.deep.mind.renderers.header

import com.github.vivchar.rendererrecyclerviewadapter.binder.ViewBinder
import com.github.vivchar.rendererrecyclerviewadapter.binder.ViewFinder
import com.morozov.psychology.R

class HeaderViewBinder: ViewBinder.Binder<HeaderModel> {
    override fun bindView(model: HeaderModel, finder: ViewFinder, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) {
            finder.setText(R.id.textRend, model.headerName)
        }
    }
}