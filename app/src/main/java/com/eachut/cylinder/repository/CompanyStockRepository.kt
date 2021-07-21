package com.eachut.cylinder.repository

import com.eachut.cylinder.api.CompanyStockAPI
import com.eachut.cylinder.api.MyApiRequest
import com.eachut.cylinder.api.ServiceBuilder
import com.eachut.cylinder.entity.CompanyStock
import com.eachut.cylinder.response.CompanyStockResponse

class CompanyStockRepository : MyApiRequest() {
    private val CompanyStockAPI =
        ServiceBuilder.buildService(com.eachut.cylinder.api.CompanyStockAPI::class.java)

    //Add Company Stock

    suspend fun addcompanystock(companyStock: CompanyStock):CompanyStockResponse{
        return apiRequest {
            CompanyStockAPI.addcompanystock(companyStock)
        }
    }
}