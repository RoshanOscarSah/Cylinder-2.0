package com.eachut.cylinder.response

import com.eachut.cylinder.entity.ScheduleExtraWork

data class ScheduleExtraWorkResponse(
    val success : Boolean? = null,
    val message : String? = null,
    val data : ScheduleExtraWork ? =null
)
