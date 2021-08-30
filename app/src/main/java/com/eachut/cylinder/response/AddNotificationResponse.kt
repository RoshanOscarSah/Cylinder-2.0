package com.eachut.cylinder.response

import com.eachut.cylinder.entity.NotificationHistory

data class AddNotificationResponse(
    val success : Boolean ? = null,
    val message : String ? = null,
    val notificationHistory: NotificationHistory ? = null
)
