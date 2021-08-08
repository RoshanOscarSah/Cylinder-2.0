package com.eachut.cylinder.response

import com.eachut.cylinder.entity.ScheduleResellerStock

data class ScheduleResellerStockResponse(
    val success : Boolean ? = null,
    val message : String ? = null,
    val data : ScheduleResellerStock ? = null
)
