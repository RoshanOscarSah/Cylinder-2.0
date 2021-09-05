package com.eachut.cylinder.api

import com.eachut.cylinder.entity.ResellerStock
import com.eachut.cylinder.response.*
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

    @GET("/profile/{id}")
    suspend fun ProfileDetails(
//        @Header("Authorization") token : String,
        @Path("id") id: String,
    ): Response<GetProfileDetailsResponse>


}