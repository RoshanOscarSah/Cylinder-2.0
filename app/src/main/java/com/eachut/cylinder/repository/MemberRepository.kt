package com.eachut.cylinder.repository

import com.eachut.cylinder.api.MemberAPI
import com.eachut.cylinder.api.MyApiRequest
import com.eachut.cylinder.api.ServiceBuilder
import com.eachut.cylinder.entity.Member
import com.eachut.cylinder.response.AddNewMemberResponse
import com.eachut.cylinder.response.ChangePasswordResponse
import com.eachut.cylinder.response.LoginResponse

class MemberRepository
    : MyApiRequest() {
    private val memberAPI =
        ServiceBuilder.buildService(MemberAPI::class.java)

    //login Customer

    suspend fun  checkMember(Username:String,Password:String): LoginResponse {
        return apiRequest {
            memberAPI.checkMember(Username, Password)
        }
    }

//    //Add New Member Admin
//    suspend fun addnewmemberadmin(user: User): AddNewMemberResponse {
//        return apiRequest {
//            userAPI.addnewmemberadmin(user)
//        }
//    }
//
////    suspend fun changepassword(user):ChangePasswordResponse{
////        return apiRequest {
////            userAPI.changepassword(user)
////        }
////    }
//
    //change garnu pachi--by bhanubhakta
    suspend fun changePassword(Username: String, Password: String, Npassword:String): ChangePasswordResponse {
        return apiRequest {
            memberAPI.changePassword(Username, Password, Npassword)
        }
    }
    //update Product
    suspend fun updateMember(id:String, member: Member):AddNewMemberResponse{
        return apiRequest {
            memberAPI.updateMember(id, member)
        }
    }



}