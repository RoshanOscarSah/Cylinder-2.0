package com.eachut.cylinder.api

import com.eachut.cylinder.entity.ScheduleExtraWork
import com.eachut.cylinder.response.GetExtraWorkSchedule
import com.eachut.cylinder.response.GetScheduleResellerStockResponse
import com.eachut.cylinder.response.ScheduleExtraWorkResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ScheduleExtraWorkAPI {

    //Schedule Extra Work
    @POST("/schedule/addSchedule")
    suspend fun addExtraWorkSchedule(
        @Body scheduleExtraWork: ScheduleExtraWork
    ): Response<ScheduleExtraWorkResponse>

    @GET("/schedule/getSchedule")
    suspend fun getExtraWorkSchedule(
//        @Header("Authorization") token : String
    ): Response<GetExtraWorkSchedule>
}