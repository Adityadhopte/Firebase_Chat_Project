package com.example.fcmchatapp.notifications.entity

data class PushNotification(
    val data: NotificationData,
    val to: String
)