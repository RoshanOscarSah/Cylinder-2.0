package com.eachut.cylinder.repository

import com.eachut.cylinder.api.CompanyStockAPI
import com.eachut.cylinder.api.MyApiRequest
import com.eachut.cylinder.api.ServiceBuilder
import com.eachut.cylinder.entity.CompanyStock
import com.eachut.cylinder.response.*

class CompanyStockRepository : MyApiRequest() {
    private val companyStockAPI =
        ServiceBuilder.buildService(CompanyStockAPI::class.java)

    //Add Company Stock

    suspend fun addCompanyStock(companyStock: CompanyStock):CompanyStockResponse{
        return apiRequest {
            companyStockAPI.addCompanyStock(companyStock)
        }
    }

    suspend fun singlecompanyStockList(id: String): GetSingleCompanyStockResponse {
        return apiRequest {
            companyStockAPI.singlecompanyStockList(id)
        }
    }


    suspend fun CompanyDetails(id: String): GetCompanyDetailsResponse {
        return apiRequest {
            companyStockAPI.CompanyDetails(id)
        }
    }



}