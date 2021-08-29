package com.example.cylinderwearos.api
import com.example.cylinderwearos.response.GetbestSellingresponse
import retrofit2.Response
import retrofit2.http.*

interface StockAPI {

    //Get Best Selling Cylinder
    @GET("bestSelling")
    suspend fun bestSelling(
//        @Header("Authorization") token : String,
    ): Response<GetbestSellingresponse>

}