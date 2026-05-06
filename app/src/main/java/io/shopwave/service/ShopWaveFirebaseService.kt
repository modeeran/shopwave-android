package io.shopwave.service

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import io.shopwave.data.remote.NotificationApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ShopWaveFirebaseService : FirebaseMessagingService() {
    @Inject lateinit var notificationApi: NotificationApi

    override fun onNewToken(token: String) {
        CoroutineScope(Dispatchers.IO).launch {
            notificationApi.registerToken(token, platform = "android")
        }
    }

    override fun onMessageReceived(message: RemoteMessage) {
        // Build and show notification
        val title = message.notification?.title ?: return
        val body  = message.notification?.body ?: return
        showNotification(title, body, message.data)
    }

    private fun showNotification(title: String, body: String, data: Map<String, String>) {
        // NotificationCompat.Builder ...
    }
}
