package com.eachut.cylinder.repository

import com.eachut.cylinder.api.MyApiRequest
import com.eachut.cylinder.api.ResellerStockAPI
import com.eachut.cylinder.api.ServiceBuilder
import com.eachut.cylinder.entity.ResellerStock
import com.eachut.cylinder.response.GetAllResellerStockResponse
import com.eachut.cylinder.response.GetProfileDetailsResponse
import com.eachut.cylinder.response.GetSingleResellerStockResponse
import com.eachut.cylinder.response.ResellerStockResponse
import com.eachut.cylinder.ui.profiles.GetResellerProfile

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

    suspend fun ProfileDetails(id: String): GetProfileDetailsResponse {
        return apiRequest {
            resellerStockAPI.ProfileDetails(id)
        }
    }

    suspend fun resellerStockList():GetAllResellerStockResponse{
        return apiRequest {
            resellerStockAPI.allresellerStockList()
        }
    }


}