package com.eachut.cylinder.repository

import com.eachut.cylinder.api.MyApiRequest
import com.eachut.cylinder.api.ResellerStockAPI
import com.eachut.cylinder.api.ServiceBuilder
import com.eachut.cylinder.entity.ResellerStock
import com.eachut.cylinder.response.GetSingleResellerStockResponse
import com.eachut.cylinder.response.ResellerStockResponse

class ResellerStockRepository: MyApiRequest() {
    private val resellerStockAPI =
        ServiceBuilder.buildService(ResellerStockAPI::class.java)

    //Add Reseller Stock

    suspend fun addResellerStock(resellerStock: ResellerStock): ResellerStockResponse {
        return apiRequest {
            resellerStockAPI.addResellerStock(resellerStock)
        }
    }

    suspend fun singleresellerStockList(id: String): GetSingleResellerStockResponse {
        return apiRequest {
            resellerStockAPI.singleresellerStockList(id)
        }
    }
}