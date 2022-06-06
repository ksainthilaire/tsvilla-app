package com.tsvilla.optimus.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Build
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate.*

const val KEY_SHARED_PREFS: String = "KEY-SHARED-PREFS"
const val KEY_DARK_MODE: String = "KEY-DARK-MODE"

fun Context.isDarkModeEnabled(): Boolean {
    val prefs = getSharedPreferences(KEY_SHARED_PREFS, MODE_PRIVATE)
    return prefs.getBoolean(KEY_DARK_MODE, false)
}

fun Context.applyDarkMode() {
    setDefaultNightMode(if (isDarkModeEnabled()) MODE_NIGHT_YES else MODE_NIGHT_NO)
}

fun Context.setDarkMode(mode: Boolean) {
    val editor = getSharedPreferences(KEY_SHARED_PREFS, MODE_PRIVATE).edit()
    editor.putBoolean(KEY_DARK_MODE, mode)
    editor.apply()

    setDefaultNightMode(if (mode) MODE_NIGHT_YES else MODE_NIGHT_NO)
}


fun Context.verifyAvailableNetwork():Boolean{
    val connectivityManager=(this as AppCompatActivity).getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo=connectivityManager.activeNetworkInfo
    return  networkInfo!=null && networkInfo.isConnected
}

/*
fun Context.startFgService() {
    Log.d("TAG", "startFgService()")
    val intent = Intent(this, AppService::class.java)
    intent.action = AppService.ACTION_START_FOREGROUND_SERVICE
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        startForegroundService(intent)
    } else {
        startService(intent)
    }
}

fun Context.stopFgService() {
    val intent = Intent(this, AppService::class.java)
    intent.action = AppService.ACTION_STOP_FOREGROUND_SERVICE
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        startForegroundService(intent)
    } else {
        startService(intent)
    }
}
 */