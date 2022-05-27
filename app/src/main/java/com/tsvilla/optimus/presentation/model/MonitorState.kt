package com.tsvilla.optimus.presentation.model

data class MonitorState(
    val timeElapsed: Int = 0,
    val currentBPM: Int = 0,
    val isBatteryLow: Boolean = false,
    val isPaused: Boolean = false
) : BaseState