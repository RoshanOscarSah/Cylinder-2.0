package com.eachut.cylinder.api

import com.eachut.cylinder.entity.ResellerStock
import com.eachut.cylinder.response.GetAllResellerResponse
import com.eachut.cylinder.response.GetAllResellerStockResponse
import com.eachut.cylinder.response.GetSingleResellerStockResponse
import com.eachut.cylinder.response.ResellerStockResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ResellerStockAPI {

    //Add Reseller Stock
    @POST("/resellerStock")
    suspend fun addResellerStock(
        @Body resellerStock : ResellerStock
    ): Response<ResellerStockResponse>


    @GET("/resellerStockList")
    suspend fun allresellerStockList(
//        @Header("Authorization") token : String,
    ): Response<GetAllResellerStockResponse>

    @GET("/resellerStockList/{id}")
    suspend fun singleresellerStockList(
//        @Header("Authorization") token : String,
        @Path("id") id: String,
    ): Response<GetSingleResellerStockResponse>
}