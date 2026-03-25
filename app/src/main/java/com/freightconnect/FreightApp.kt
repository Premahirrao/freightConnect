package com.freightconnect

import android.app.Application
import com.google.firebase.FirebaseApp

class FreightApp : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}
