package com.morozov.psychology.ui.fragments.deep.mind.renderers.text.and.edit.state.save

import com.github.vivchar.rendererrecyclerviewadapter.ViewHolder
import com.github.vivchar.rendererrecyclerviewadapter.ViewState
import com.github.vivchar.rendererrecyclerviewadapter.ViewStateProvider
import com.morozov.psychology.ui.fragments.deep.mind.renderers.text.and.edit.TextAndEditModel

class EditViewStateProvider: ViewStateProvider<TextAndEditModel, ViewHolder> {
    override fun createViewState(holder: ViewHolder): ViewState<ViewHolder> {
        return EditViewState(holder)
    }

    override fun createViewStateID(model: TextAndEditModel): Int {
        return model.position
    }
}