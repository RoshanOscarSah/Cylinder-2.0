package com.eachut.cylinder.repository

import com.eachut.cylinder.api.MyApiRequest
import com.eachut.cylinder.api.ServiceBuilder
import com.eachut.cylinder.entity.ResellerStock
import com.eachut.cylinder.response.ResellerStockResponse

class ResellerStockRepository: MyApiRequest() {
    private val ResellerStockAPI =
        ServiceBuilder.buildService(com.eachut.cylinder.api.ResellerStockAPI::class.java)

    //Add Reseller Stock

    suspend fun addresellerstock(resellerStock: ResellerStock): ResellerStockResponse {
        return apiRequest {
          ResellerStockAPI.addresellerstock(resellerStock)
        }
    }
}