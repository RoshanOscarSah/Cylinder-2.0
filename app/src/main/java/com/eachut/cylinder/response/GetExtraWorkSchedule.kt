package com.eachut.cylinder.response

import com.eachut.cylinder.entity.ScheduleExtraWork

data class GetExtraWorkSchedule(
    val success : Boolean ? = null,
    val message : String ? = null,
    val data : ArrayList<ScheduleExtraWork> ? = null
)