package com.tsvilla.optimus.presentation

import android.R
import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat


class AppService : Service() {

    companion object {
        const val TAG = "AppService"

        const val ACTION_START_FOREGROUND_SERVICE = "ACTION_START_FOREGROUND_SERVICE"
        const val ACTION_STOP_FOREGROUND_SERVICE = "ACTION_STOP_FOREGROUND_SERVICE"
        const val ACTION_PAUSE = "ACTION_PAUSE"
        const val ACTION_PLAY = "ACTION_PLAY"
    }

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val action = intent!!.action
        when (action) {
            ACTION_START_FOREGROUND_SERVICE -> {
                startForegroundService()
                Toast.makeText(
                    applicationContext,
                    "Foreground service is started.",
                    Toast.LENGTH_LONG
                ).show()
            }
            ACTION_STOP_FOREGROUND_SERVICE -> {
                stopForegroundService()
                Toast.makeText(
                    applicationContext,
                    "Foreground service is stopped.",
                    Toast.LENGTH_LONG
                ).show()
            }
            ACTION_PLAY -> Toast.makeText(
                applicationContext,
                "You click Play button.",
                Toast.LENGTH_LONG
            ).show()
            ACTION_PAUSE -> Toast.makeText(
                applicationContext,
                "You click Pause button.",
                Toast.LENGTH_LONG
            ).show()
        }

        return super.onStartCommand(intent, flags, startId)
    }

    private fun startForegroundService() {
        Log.d(TAG, "Start foreground service.")

        val intent = Intent()
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        val builder: NotificationCompat.Builder = NotificationCompat.Builder(this)

        val bigTextStyle: NotificationCompat.BigTextStyle = NotificationCompat.BigTextStyle()
        bigTextStyle.setBigContentTitle("Music player implemented by foreground service.")
        bigTextStyle.bigText("Android foreground service is a android service which can run in foreground always, it can be controlled by user via notification.")

        builder.setStyle(bigTextStyle)
        builder.setWhen(System.currentTimeMillis())
        // builder.setSmallIcon(R.mipmap.ic_launcher)
        //val largeIconBitmap = BitmapFactory.decodeResource(resources, R.drawable.icon_music_32)
        //builder.setLargeIcon(largeIconBitmap)
        // Make the notification max priority.
        builder.setPriority(NotificationCompat.PRIORITY_MAX)
        // Make head-up notification.
        builder.setFullScreenIntent(pendingIntent, true)
        // Add Play button intent in notification.
        val playIntent = Intent(this, AppService::class.java)
        playIntent.action = ACTION_PLAY
        val pendingPlayIntent = PendingIntent.getService(this, 0, playIntent, 0)
        val playAction: NotificationCompat.Action =
            NotificationCompat.Action(R.drawable.ic_media_play, "Play", pendingPlayIntent)
        builder.addAction(playAction)
        // Add Pause button intent in notification.
        val pauseIntent = Intent(this, AppService::class.java)
        pauseIntent.action = ACTION_PAUSE
        val pendingPrevIntent = PendingIntent.getService(this, 0, pauseIntent, 0)
        val prevAction: NotificationCompat.Action =
            NotificationCompat.Action(R.drawable.ic_media_pause, "Pause", pendingPrevIntent)
        builder.addAction(prevAction)
        // Build the notification.
        val notification: Notification = builder.build()
        // Start foreground service.
        startForeground(1, notification)
    }

    private fun stopForegroundService() {
        Log.d(TAG, "Stop foreground service.")
        stopForeground(true)
        stopSelf()
    }
}