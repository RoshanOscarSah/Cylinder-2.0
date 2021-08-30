package com.eachut.cylinder.repository

import com.eachut.cylinder.api.MyApiRequest
import com.eachut.cylinder.api.NotificationAPI
import com.eachut.cylinder.api.ServiceBuilder
import com.eachut.cylinder.entity.NotificationHistory
import com.eachut.cylinder.response.AddNotificationResponse
import com.eachut.cylinder.response.GetNotificationHistory

class NotificationRepository:MyApiRequest() {
    private val notificationAPI =
        ServiceBuilder.buildService(NotificationAPI::class.java)

    //Add Notification
    suspend fun addNotification(notificationHistory: NotificationHistory):AddNotificationResponse{
        return apiRequest {
            notificationAPI.addNotification(notificationHistory)
        }
    }

    //get notification history
    suspend fun notificationHistoryList():GetNotificationHistory{
        return apiRequest {
            notificationAPI.notificationHistorylist()
        }
    }
}