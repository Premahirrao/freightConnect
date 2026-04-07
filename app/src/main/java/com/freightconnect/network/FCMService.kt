package com.freightconnect.network

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.freightconnect.R
import com.freightconnect.repository.FreightRepository
import com.freightconnect.ui.activities.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Task 12: FCMService - Handle push notifications from Firebase Cloud Messaging
 * 
 * Handles:
 * - Interest received notifications
 * - Interest accepted/rejected notifications
 * - Cargo/route status updates
 * - FCM token management
 */
class FCMService : FirebaseMessagingService() {

    companion object {
        const val CHANNEL_ID = "freight_notifications"
        const val CHANNEL_NAME = "Freight Notifications"
        const val CHANNEL_ID_HIGH = "freight_notifications_high"
        const val CHANNEL_NAME_HIGH = "Freight Alerts"
    }

    private val repo = FreightRepository()

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        // Save token to Firestore for sending notifications
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val uid = repo.currentUid()
                if (uid.isNotEmpty()) {
                    repo.updateFCMToken(uid, token)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        
        val title = message.notification?.title ?: message.data["title"] ?: "FreightConnect"
        val body = message.notification?.body ?: message.data["body"] ?: ""
        val notificationType = message.data["type"] ?: "GENERAL"
        
        // Task 12: Route notification based on type
        when (notificationType) {
            "INTEREST_RECEIVED" -> {
                showNotificationHighPriority(title, body, "🔔 New Interest!")
            }
            "INTEREST_ACCEPTED" -> {
                showNotificationHighPriority(title, body, "✅ Interest Accepted!")
            }
            "INTEREST_REJECTED" -> {
                showNotification(title, body)
            }
            "ROUTE_BOOKED" -> {
                showNotificationHighPriority(title, body, "📍 Route Booked!")
            }
            "CARGO_BOOKED" -> {
                showNotificationHighPriority(title, body, "📦 Cargo Booked!")
            }
            else -> {
                showNotification(title, body)
            }
        }
    }

    /**
     * Show high-priority notification (interest/booking updates)
     */
    private fun showNotificationHighPriority(title: String, body: String, prefix: String = "") {
        createChannel()
        
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            // Optional: Add deep link to interests fragment
            putExtra("navigate_to", "interests")
        }
        
        val pendingIntent = PendingIntent.getActivity(
            this, System.currentTimeMillis().toInt(), intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(this, CHANNEL_ID_HIGH)
            .setSmallIcon(R.drawable.ic_truck_logo)
            .setContentTitle("$prefix $title")
            .setContentText(body)
            .setStyle(NotificationCompat.BigTextStyle().bigText(body))
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setVibrate(longArrayOf(0, 500))
            .setSound(android.provider.Settings.System.DEFAULT_NOTIFICATION_URI)
            .build()

        (getSystemService(NOTIFICATION_SERVICE) as NotificationManager)
            .notify(System.currentTimeMillis().toInt(), notification)
    }

    /**
     * Show standard notification
     */
    private fun showNotification(title: String, body: String) {
        createChannel()

        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        
        val pendingIntent = PendingIntent.getActivity(
            this, System.currentTimeMillis().toInt(), intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_truck_logo)
            .setContentTitle(title)
            .setContentText(body)
            .setStyle(NotificationCompat.BigTextStyle().bigText(body))
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        (getSystemService(NOTIFICATION_SERVICE) as NotificationManager)
            .notify(System.currentTimeMillis().toInt(), notification)
    }

    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Standard channel
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            
            // High priority channel for alerts
            val channelHigh = NotificationChannel(
                CHANNEL_ID_HIGH,
                CHANNEL_NAME_HIGH,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                enableVibration(true)
                enableLights(true)
            }
            
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
            manager.createNotificationChannel(channelHigh)
        }
    }
}
