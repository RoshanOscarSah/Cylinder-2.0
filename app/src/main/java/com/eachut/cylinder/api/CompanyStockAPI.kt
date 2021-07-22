package com.eachut.cylinder.api

import com.eachut.cylinder.entity.CompanyStock
import com.eachut.cylinder.response.CompanyStockResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface CompanyStockAPI {

    //Add Company Stock
    @POST("/CompanyStock")
    suspend fun addCompanyStock(
        @Body companyStock: CompanyStock
    ): Response<CompanyStockResponse>
}