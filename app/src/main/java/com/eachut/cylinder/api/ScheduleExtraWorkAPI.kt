package com.eachut.cylinder.api

import com.eachut.cylinder.entity.ScheduleExtraWork
import com.eachut.cylinder.response.ScheduleExtraWorkResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ScheduleExtraWorkAPI {

    //Schedule Extra Work
    @POST("/schedule/addSchedule")
    suspend fun addExtraWorkSchedule(
        @Body scheduleExtraWork: ScheduleExtraWork
    ): Response<ScheduleExtraWorkResponse>
}