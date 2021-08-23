package com.eachut.cylinder.repository

import com.eachut.cylinder.api.MyApiRequest
import com.eachut.cylinder.api.ScheduleResellerStockAPI
import com.eachut.cylinder.api.ServiceBuilder
import com.eachut.cylinder.entity.ScheduleResellerStock
import com.eachut.cylinder.response.GetScheduleResellerStockResponse
import com.eachut.cylinder.response.ScheduleResellerStockResponse

class ScheduleResellerStockRepository: MyApiRequest() {
    private val scheduleResellerStockAPI =
        ServiceBuilder.buildService(ScheduleResellerStockAPI::class.java)

    //Add Schedule Reseller Stock
    suspend fun addScheduleResellerStock(scheduleResellerStock: ScheduleResellerStock) : ScheduleResellerStockResponse{
        return apiRequest {
            scheduleResellerStockAPI.addScheduleResellerStock(scheduleResellerStock)
        }
    }

    suspend fun getScheduleResellerStock() : GetScheduleResellerStockResponse{
        return apiRequest {
            scheduleResellerStockAPI.getScheduleResellerStock()
        }
    }

}