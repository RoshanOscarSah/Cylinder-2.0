package com.eachut.cylinder.repository

import com.eachut.cylinder.api.MyApiRequest
import com.eachut.cylinder.api.ServiceBuilder
import com.eachut.cylinder.api.UserAPI
import com.eachut.cylinder.entity.User
import com.eachut.cylinder.response.AddNewMemberResponse
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

    //    Login Member
    suspend fun  checkMember(username:String,password:String): LoginResponse {
        return apiRequest {
            userAPI.checkMember(username,password)
        }
    }
}