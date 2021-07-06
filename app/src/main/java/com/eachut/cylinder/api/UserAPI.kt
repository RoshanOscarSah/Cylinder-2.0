package com.eachut.cylinder.api

import com.eachut.cylinder.response.LoginResponse
import retrofit2.Response
import retrofit2.http.*

interface UserAPI {

    //Register User
//
//    @POST("customer/insert")
//    suspend fun registerCustomer(
//        @Body customer : Customer
//    ): Response<RegisterResponse>

    //Login Customer
    @FormUrlEncoded
    @POST("user/login")
    suspend fun checkCustomer(
            @Field("Phonenumber") Phonenumber : String,
            @Field("Password") Password : String
    ): Response<LoginResponse>









}