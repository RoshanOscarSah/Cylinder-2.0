package com.eachut.cylinder.repository

import com.eachut.cylinder.api.MyApiRequest
import com.eachut.cylinder.api.ServiceBuilder
import com.eachut.cylinder.api.UserAPI
import com.eachut.cylinder.entity.User
import com.eachut.cylinder.response.AddNewMemberResponse
import com.eachut.cylinder.response.ChangePasswordResponse
import com.eachut.cylinder.response.LoginResponse

class UserRepository
    : MyApiRequest(){

    private val userAPI =
        ServiceBuilder.buildService(UserAPI::class.java)

    //login Customer

    suspend fun  checkUser(username:String,password:String): LoginResponse {
        return apiRequest {
            userAPI.checkUser(username,password)
        }
    }
    //Add New Member Admin
    suspend fun addnewmemberadmin(user: User):AddNewMemberResponse {
        return apiRequest {
            userAPI.addnewmemberadmin(user)
        }
    }

//    suspend fun changepassword(user):ChangePasswordResponse{
//        return apiRequest {
//            userAPI.changepassword(user)
//        }
//    }

    //change garnu pachi

    suspend fun changepassword(username: String, password: String, new_password:String):ChangePasswordResponse {
        return apiRequest {
            userAPI.changepassword(username, password, new_password)
        }
    }
}