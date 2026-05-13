package com.example.vidyarthibus.ui.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.example.vidyarthibus.R

fun showBusNotification(
    context: Context,
    title: String,
    message: String
) {

    val channelId = "bus_alerts"

    val notificationManager =
        context.getSystemService(
            Context.NOTIFICATION_SERVICE
        ) as NotificationManager

    val channel = NotificationChannel(
        channelId,
        "Bus Alerts",
        NotificationManager.IMPORTANCE_HIGH
    )

    notificationManager.createNotificationChannel(channel)

    val notification =
        NotificationCompat.Builder(
            context,
            channelId
        )
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(
                NotificationCompat.PRIORITY_HIGH
            )
            .build()

    notificationManager.notify(
        System.currentTimeMillis().toInt(),
        notification
    )
}