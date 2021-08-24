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

    //get gas cylinder
    suspend fun gascylindersold(): Getgascylinderresponse {
        return apiRequest {
            StockAPI.gascylindersold()
        }
    }

    //get best selling
    suspend fun bestSelling(): GetbestSellingresponse {
        return apiRequest {
            StockAPI.bestSelling()
        }
    }

    //get next Order
    suspend fun nextOrder(): GetnextOrderresponse {
        return apiRequest {
            StockAPI.nextOrder()
        }
    }

    //get profit and loss
    suspend fun profitLoss(): GetProfitLossresponse {
        return apiRequest {
            StockAPI.profitloss()
        }
    }

}