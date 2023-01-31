package com.example.ds_movies.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // Here Write Action Code
        val state:Int = intent.getIntExtra("state",-1)
        if(state == 1)
        {
            Toast.makeText(context,"Headset is Connected",Toast.LENGTH_LONG).show()
        }
        else
        {
            Toast.makeText(context,"Headset is Disconnected",Toast.LENGTH_LONG).show()
        }

    }
}

