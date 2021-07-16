package com.eachut.cylinder.api


import com.eachut.cylinder.entity.Reseller
import com.eachut.cylinder.response.AddNewResellerResponse
import retrofit2.Response
import retrofit2.http.*

interface ResellerAPI {
    //Add new Resseller
    @POST("/newReseller")
    suspend fun addNewReseller(
        @Body reseller: Reseller
    ): Response<AddNewResellerResponse>
}