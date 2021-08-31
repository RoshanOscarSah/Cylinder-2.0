package com.eachut.cylinder.api

import com.eachut.cylinder.entity.NotificationHistory
import com.eachut.cylinder.response.AddNotificationResponse
import com.eachut.cylinder.response.GetNotificationHistory
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface NotificationAPI {
    //Adding Notification
    @POST("/notification")
    suspend fun addNotification(
        @Body notificationHistory: NotificationHistory
    ):Response<AddNotificationResponse>

    @GET("/notification")
    suspend fun notificationHistorylist(
    ):Response<GetNotificationHistory>
}