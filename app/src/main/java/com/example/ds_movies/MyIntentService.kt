package com.example.ds_movies

import android.app.IntentService
import android.content.Intent


class MyIntentService : IntentService("MyIntentService") {
    override fun onCreate() {
        super.onCreate()
    }
    override fun onHandleIntent(intent: Intent?) {
        //Here Write Our Action Code
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}