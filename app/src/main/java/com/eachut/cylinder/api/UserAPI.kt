package com.eachut.cylinder.api

import com.eachut.cylinder.entity.User
import com.eachut.cylinder.response.AddNewMemberResponse
import com.eachut.cylinder.response.LoginResponse
import retrofit2.Response
import retrofit2.http.*

interface UserAPI {


    //Login Admin
    @FormUrlEncoded
    @POST("/admin/login")
    suspend fun checkUser(
            @Field("Username") Username : String,
            @Field("Password") Password : String
    ): Response<LoginResponse>

    //Add Member Admin
    @POST("/admin/profile/addMember")
    suspend fun addnewmemberadmin(
        @Body user: User
    ):Response<AddNewMemberResponse>

    // Login Member
    @FormUrlEncoded
    @POST("/login")
    suspend fun checkMember(
        @Field("Username") Username : String,
        @Field("Password") Password : String
    ): Response<LoginResponse>

    //Change Password
//
//    @PUT("/changepassword")
//    suspend fun changepassword(
//        @Header("Authorization") token:String,
//        @Path("id")id:String,
//        @Body user: User
//    ):Response<ChangePasswordResponse>


}