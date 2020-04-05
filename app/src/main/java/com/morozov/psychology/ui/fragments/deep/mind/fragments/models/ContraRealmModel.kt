package com.morozov.psychology.ui.fragments.deep.mind.fragments.models

import com.morozov.psychology.ui.fragments.deep.mind.renderers.text.and.percent.TextAndPercentModel
import io.realm.RealmObject

open class ContraRealmModel(
    var thinkId: Long = -1,
    var text: String = "",
    var percent: Int = 0
): RealmObject() {
    fun toTextAndPercentModel(position: Int): TextAndPercentModel {
        return TextAndPercentModel(position, text, percent)
    }
}