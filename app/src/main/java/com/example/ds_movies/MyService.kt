package com.example.ds_movies

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder

class MyService : Service() {

    lateinit var mp: MediaPlayer
    override fun onCreate() {
        super.onCreate()
        mp = MediaPlayer.create(this,R.raw.aleky_eyoun)
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if(!mp.isPlaying)
        mp.start()
        return START_REDELIVER_INTENT
    }

    override fun onDestroy() {
        super.onDestroy()
        if(mp.isPlaying){
            mp.stop()
            mp.release()
        }
    }
    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}