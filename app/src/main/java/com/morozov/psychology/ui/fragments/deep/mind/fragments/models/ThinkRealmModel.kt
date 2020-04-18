package com.morozov.psychology.ui.fragments.deep.mind.fragments.models

import com.morozov.psychology.ui.fragments.deep.mind.renderers.card.and.text.CardAndTextModel
import io.realm.RealmObject

open class ThinkRealmModel(
    var id: Long = -1,
    var text: String = "",
    var percent: Int = 0,
    var timeCreate: Long = 0L
): RealmObject() {
    fun toCardAndText(position: Int): CardAndTextModel {
        return CardAndTextModel(position, text, percent)
    }
}