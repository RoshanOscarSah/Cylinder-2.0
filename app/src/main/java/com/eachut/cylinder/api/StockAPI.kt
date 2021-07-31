package com.eachut.cylinder.api
import com.eachut.cylinder.entity.Company
import com.eachut.cylinder.entity.Reseller
import com.eachut.cylinder.entity.Stock
import com.eachut.cylinder.response.*
import retrofit2.Response
import retrofit2.http.*

interface StockAPI {

//    view stock
    @GET("/stockDetails")
    suspend fun viewStock(
//        @Header("Authorization") token : String,
    ): Response<StockResponse>

    //update Stock
    @POST("/addStock")
    suspend fun updateStock(
        @Body stock: Stock
    ):Response<StockResponse>



}