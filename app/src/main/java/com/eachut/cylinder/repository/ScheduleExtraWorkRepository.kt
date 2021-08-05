package com.eachut.cylinder.repository

import com.eachut.cylinder.api.MyApiRequest
import com.eachut.cylinder.api.ScheduleExtraWorkAPI
import com.eachut.cylinder.api.ServiceBuilder
import com.eachut.cylinder.entity.ScheduleExtraWork
import com.eachut.cylinder.response.GetExtraWorkSchedule
import com.eachut.cylinder.response.GetScheduleResellerStockResponse
import com.eachut.cylinder.response.ScheduleExtraWorkResponse

class ScheduleExtraWorkRepository: MyApiRequest() {
    private val scheduleExtraWorkAPI =
        ServiceBuilder.buildService(ScheduleExtraWorkAPI::class.java)

    //add Extra Work Schedule
    suspend fun addExtraWorkSchedule(scheduleExtraWork: ScheduleExtraWork): ScheduleExtraWorkResponse{
        return apiRequest {
            scheduleExtraWorkAPI.addExtraWorkSchedule(scheduleExtraWork)
        }
    }

    //get Extra Work Schedule
    suspend fun getExtraWorkSchedule() : GetExtraWorkSchedule {
        return apiRequest {
            scheduleExtraWorkAPI.getExtraWorkSchedule()
        }
    }
}