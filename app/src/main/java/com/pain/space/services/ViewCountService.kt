package com.pain.space.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.google.firebase.database.FirebaseDatabase

class ViewCountService : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val key = intent?.getStringExtra("key") ?: return START_NOT_STICKY

        Thread {
            val db = FirebaseDatabase.getInstance()
            val ref = db.reference.child(key).child("live_views")
            ref.get().addOnSuccessListener { snapshot ->
                val value = snapshot.getValue(Int::class.java) ?: 0
                ref.setValue(value - 1)
            }
        }.start()
        stopSelf()
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
