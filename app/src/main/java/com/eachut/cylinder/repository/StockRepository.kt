package com.eachut.cylinder.repository

import com.eachut.cylinder.api.StockAPI
import com.eachut.cylinder.api.MyApiRequest
import com.eachut.cylinder.api.ServiceBuilder
import com.eachut.cylinder.entity.Company
import com.eachut.cylinder.entity.Reseller
import com.eachut.cylinder.entity.Stock
import com.eachut.cylinder.response.*

class StockRepository: MyApiRequest() {
    private val StockAPI =
        ServiceBuilder.buildService(StockAPI::class.java)

    //get stock
    suspend fun viewStock(): StockResponse {
        return apiRequest {
            StockAPI.viewStock()
        }
    }

    //    update stock
    suspend fun updateStock(stock: Stock): StockResponse {
        return apiRequest {
            StockAPI.updateStock(stock)
        }
    }



}