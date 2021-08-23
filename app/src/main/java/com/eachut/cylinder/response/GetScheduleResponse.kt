package com.eachut.cylinder.response


data class GetScheduleResponse (

        val success : Boolean?=null,
        val message : String?=null,
        val data : ScheduleResellerStockResponse?=null
)