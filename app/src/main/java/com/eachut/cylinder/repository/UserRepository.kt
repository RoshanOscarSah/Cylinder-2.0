package com.eachut.cylinder.repository

import com.eachut.cylinder.api.MyApiRequest
import com.eachut.cylinder.api.ServiceBuilder
import com.eachut.cylinder.api.UserAPI
import com.eachut.cylinder.response.LoginResponse

class UserRepository
    : MyApiRequest(){

    private val userAPI =
        ServiceBuilder.buildService(UserAPI::class.java)

    //login Customer

    suspend fun  checkUser(Phonenumber:String,Password:String): LoginResponse {
        return apiRequest {
            userAPI.checkCustomer(Phonenumber,Password)
        }
    }
}