package com.eachut.cylinder.response

import com.eachut.cylinder.entity.NotificationHistory

data class GetNotificationHistory(
    val success : Boolean ? = null,
    val message : String ? = null,
    val data : NotificationHistory ? = null
)
