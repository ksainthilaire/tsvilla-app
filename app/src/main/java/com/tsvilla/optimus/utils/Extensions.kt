package com.tsvilla.optimus.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE

const val KEY_SHARED_PREFS: String = "KEY-SHARED-PREFS"
const val KEY_DARK_MODE: String = "KEY-DARK-MODE"

fun Context.getCurrentMode() : Boolean {
    val prefs = getSharedPreferences(KEY_SHARED_PREFS, MODE_PRIVATE)
    return prefs.getBoolean(KEY_DARK_MODE, false)
}

fun Context.changeMode(mode: Boolean = false) {
    val editor = getSharedPreferences(KEY_SHARED_PREFS, MODE_PRIVATE).edit()
    editor.putBoolean(KEY_DARK_MODE, mode)
    editor.apply()
}
