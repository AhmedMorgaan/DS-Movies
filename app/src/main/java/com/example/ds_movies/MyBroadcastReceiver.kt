package com.example.ds_movies

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
        else if(state == 0)
                 {
                    Toast.makeText(context,"Headset is Disconnected",Toast.LENGTH_LONG).show()
                }
                else {
                    Toast.makeText(context,"Headset is Empty",Toast.LENGTH_LONG).show()
                }
            }
        }
//        else if (intent.action.equals("android.os.action.CHARGING")){
//            if (intent.)
//        Toast.makeText(context,"USB is Connected",Toast.LENGTH_LONG).show()
//    }
//    }
