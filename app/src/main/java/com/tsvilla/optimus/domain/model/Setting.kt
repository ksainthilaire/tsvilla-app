package com.tsvilla.optimus.domain.model

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes

enum class SettingType {
    BUTTON,
    SWITCH
}

data class Setting(
    val name: String,
    val drawable: Int,
    val value: Boolean,
    val type: SettingType = SettingType.BUTTON
)
