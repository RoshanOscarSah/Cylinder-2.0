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

    //Get Gas Sold / Cylinder Sold
    @GET("/gas-cylinder-Sold")
    suspend fun gascylindersold(
//        @Header("Authorization") token : String,
    ): Response<Getgascylinderresponse>

    //Get Best Selling Cylinder
    @GET("bestSelling")
    suspend fun bestSelling(
//        @Header("Authorization") token : String,
    ): Response<GetbestSellingresponse>

    //Get Next Order
    @GET("nextorder")
    suspend fun nextOrder(
//        @Header("Authorization") token : String,
    ): Response<GetnextOrderresponse>

    //Get Profit and Loss
    @GET("/profit-loss-investment")
    suspend fun profitloss(
//        @Header("Authorization") token : String,
    ): Response<GetProfitLossresponse>

}