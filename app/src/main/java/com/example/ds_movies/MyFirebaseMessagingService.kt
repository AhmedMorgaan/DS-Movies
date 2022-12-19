package com.example.ds_movies

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    private var CHANNEL_ID :String ="Channel_X1"

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        val title = remoteMessage.notification?.title
        val text = remoteMessage.notification?.body
        Log.e("massage","Massage Received")
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.O)
        {
            val channel = NotificationChannel(CHANNEL_ID,"Morgan Movies Notification"
                ,NotificationManager.IMPORTANCE_HIGH)

          //  val nm :NotificationManager = getSystemService(NotificationManager::class.java)
            val nm :NotificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            nm.createNotificationChannel(channel)
        }

        val intent = Intent(this,SomaActivity::class.java)
        val pi :PendingIntent = PendingIntent.getActivity(this,0,intent,0)
        val bitmap :Bitmap = BitmapFactory.decodeResource(applicationContext.resources,R.drawable.love_you)
        val bitmapLargeIcon :Bitmap = BitmapFactory.decodeResource(applicationContext.resources,R.drawable.ds)

val build = NotificationCompat.Builder(this,CHANNEL_ID)
        build.setSmallIcon(R.drawable.ds_light)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setContentTitle(title)
            .setAutoCancel(true)
            .setLargeIcon(bitmapLargeIcon)
            .setStyle(NotificationCompat.BigPictureStyle().bigPicture(bitmap))
            .setContentIntent(pi)
            .setContentText(text).priority = NotificationCompat.PRIORITY_MAX


        val nmc = NotificationManagerCompat.from(this)
        nmc.notify(1,build.build())
    }

}