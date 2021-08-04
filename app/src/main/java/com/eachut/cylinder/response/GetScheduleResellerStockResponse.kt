package com.eachut.cylinder.response

import com.eachut.cylinder.entity.ScheduleResellerStock

data class GetScheduleResellerStockResponse(
    val success : Boolean ? = null,
    val message : String ? = null,
    val data : ScheduleResellerStock ? = null
)
