package com.eachut.cylinder.api

import com.eachut.cylinder.entity.User
import com.eachut.cylinder.response.AddNewMemberResponse
import com.eachut.cylinder.response.ChangePasswordResponse
import com.eachut.cylinder.response.LoginResponse
import retrofit2.Response
import retrofit2.http.*

interface UserAPI {


    //Login Admin
    @FormUrlEncoded
    @POST("/login")
    suspend fun checkUser(
        @Field("username") username : String,
        @Field("password") password : String
    ): Response<LoginResponse>

    //Add Member Admin
    @POST("/admin/profile/addMember")
    suspend fun addnewmemberadmin(
        @Body user: User
    ):Response<AddNewMemberResponse>

    //Change Password
    //changing password using token/authorization
//    @PUT("/changePassword")
//    suspend fun changepassword(
//        @Header("Authorization") token:String,
//        @Path("id")id:String,
//        @Body user: User
//    ):Response<ChangePasswordResponse>

    //changing password using username
    @FormUrlEncoded
    @PUT("/changePassword")
    suspend fun changePassword(
        @Field("username") username : String,
        @Field("password") password : String,
        @Field("new_password") new_password : String
    ):Response<ChangePasswordResponse>
}