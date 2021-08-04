package com.eachut.cylinder.api

import com.eachut.cylinder.entity.ScheduleResellerStock
import com.eachut.cylinder.response.GetScheduleResellerStockResponse
import com.eachut.cylinder.response.ScheduleResellerStockResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ScheduleResellerStockAPI {

    //Schedule Reseller Stock
    @POST("/schedule/resellerStock")
    suspend fun addScheduleResellerStock(
        @Body scheduleResellerStock: ScheduleResellerStock
    ): Response<ScheduleResellerStockResponse>

    @GET("/schedule/resellerStock")
    suspend fun getScheduleResellerStock(
//        @Header("Authorization") token : String
    ): Response<GetScheduleResellerStockResponse>
}