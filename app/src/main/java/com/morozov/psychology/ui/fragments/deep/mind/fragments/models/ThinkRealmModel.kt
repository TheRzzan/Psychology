package com.morozov.psychology.ui.fragments.deep.mind.fragments.models

import io.realm.RealmObject

open class ThinkRealmModel(
    var id: Long = -1,
    var text: String = "",
    var percent: Int = 0
): RealmObject()