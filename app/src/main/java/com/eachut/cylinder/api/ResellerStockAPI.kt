package com.eachut.cylinder.api

import com.eachut.cylinder.entity.ResellerStock
import com.eachut.cylinder.response.ResellerStockResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ResellerStockAPI {

    //Add Reseller Stock
    @POST("/resellerStock")
    suspend fun addresellerstock(
        @Body resellerStock : ResellerStock
    ): Response<ResellerStockResponse>
}