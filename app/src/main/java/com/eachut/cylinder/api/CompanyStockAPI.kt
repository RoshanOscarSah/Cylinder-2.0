package com.eachut.cylinder.api

import com.eachut.cylinder.Object.CompanyDetails
import com.eachut.cylinder.entity.CompanyStock
import com.eachut.cylinder.response.CompanyStockResponse
import com.eachut.cylinder.response.GetCompanyDetailsResponse
import com.eachut.cylinder.response.GetProfileDetailsResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CompanyStockAPI {

    //Add Company Stock
    @POST("/CompanyStock")
    suspend fun addCompanyStock(
        @Body companyStock: CompanyStock
    ): Response<CompanyStockResponse>

    @GET("/company/{id}")
    suspend fun CompanyDetails(
//        @Header("Authorization") token : String,
        @Path("id") id: String,
    ): Response<GetCompanyDetailsResponse>


}