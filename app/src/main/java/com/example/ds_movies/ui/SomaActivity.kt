package com.example.ds_movies.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ds_movies.R
import com.example.ds_movies.service.MyService

class SomaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_soma)
    }

    override fun onStart() {
        super.onStart()
        val intent = Intent(applicationContext, MyService::class.java)
        startService(intent)
    }

    override fun onStop() {
        super.onStop()
        val intent = Intent(applicationContext, MyService::class.java)
        stopService(intent)
    }
}