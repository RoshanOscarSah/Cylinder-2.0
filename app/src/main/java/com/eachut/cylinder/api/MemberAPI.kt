package com.eachut.cylinder.api

import com.eachut.cylinder.entity.Member
import com.eachut.cylinder.response.AddNewMemberResponse
import com.eachut.cylinder.response.ChangePasswordResponse
import com.eachut.cylinder.response.LoginResponse
import retrofit2.Response
import retrofit2.http.*

interface MemberAPI {
    //Login Admin
    @FormUrlEncoded
    @POST("/login")
    suspend fun checkMember(
        @Field("Username") Username : String,
        @Field("Password") Password : String
    ): Response<LoginResponse>


    //Add Member Admin
    @POST("/admin/profile/addMember")
    suspend fun addnewmemberadmin(
        @Body member: Member
    ):Response<AddNewMemberResponse>
//
//    //Change Password
//    //changing password using token/authorization
////    @PUT("/changePassword")
////    suspend fun changepassword(
////        @Header("Authorization") token:String,
////        @Path("id")id:String,
////        @Body user: User
////    ):Response<ChangePasswordResponse>
//
    //changing password using username
    @FormUrlEncoded
    @PUT("/changepassword")
    suspend fun changePassword(
        @Field("Username") Username : String,
        @Field("Password") Password : String,
        @Field("Npassword") Npassword : String
    ):Response<ChangePasswordResponse>

//    @PUT("product/updateProduct/{id}")
//    suspend fun updateProduct(
//        @Header("Authorization") token: String,
//        @Path ("id") id: String,
//        @Body product: Product
//    ): Response<AddProductResponse>

//    @GET("product/android/getAllProduct")
//    suspend fun getAllProduct(
//        @Header("Authorization") token : String,
//    ): Response<GetAllProductResponse>

}