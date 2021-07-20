package com.eachut.cylinder.api


import com.eachut.cylinder.entity.Member
import com.eachut.cylinder.entity.Reseller
import com.eachut.cylinder.response.AddNewMemberResponse
import com.eachut.cylinder.response.AddNewResellerResponse
import com.eachut.cylinder.response.GetAllResellerResponse
import com.eachut.cylinder.response.GetResellerResponse
import retrofit2.Response
import retrofit2.http.*

interface ResellerAPI {
    //Add new Resseller
    @POST("/newReseller")
    suspend fun addNewReseller(
        @Body reseller: Reseller
    ): Response<AddNewResellerResponse>

    @GET("/resellerList")
    suspend fun allresellerList(
//        @Header("Authorization") token : String,
    ): Response<GetAllResellerResponse>

    @GET("/resellerList/{id}")
    suspend fun resellerList(
//        @Header("Authorization") token : String,
        @Path ("id") id: String,
    ): Response<GetResellerResponse>


    @PUT("/reseller/update/{id}")
    suspend fun updateReseller(
//        @Header("Authorization") token: String,
        @Path ("id") id: String,
        @Body reseller: Reseller
    ): Response<AddNewResellerResponse>

}