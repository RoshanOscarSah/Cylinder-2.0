package com.eachut.cylinder.api

import com.eachut.cylinder.Object.CompanyDetails
import com.eachut.cylinder.entity.CompanyStock
import com.eachut.cylinder.response.*
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

//    singlecompanyStockList
    @GET("/companyStockList/{id}")
    suspend fun singlecompanyStockList(
    //        @Header("Authorization") token : String,
        @Path("id") id: String,
    ): Response<GetSingleCompanyStockResponse>

}