package com.eachut.cylinder.repository

import com.eachut.cylinder.api.MemberAPI
import com.eachut.cylinder.api.MyApiRequest
import com.eachut.cylinder.api.ServiceBuilder
import com.eachut.cylinder.entity.Member
import com.eachut.cylinder.response.*

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

    //Add New Member Admin
    suspend fun addnewmemberadmin(member: Member): AddNewMemberResponse {
        return apiRequest {
            memberAPI.addnewmemberadmin(member)
        }
    }
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

    //get member
    suspend fun allmemberList(): GetAllMemberResponse {
        return apiRequest {
            memberAPI.allmemberList()
        }
    }

    //get single member
    suspend fun memberList(id:String): GetMemberResponse {
        return apiRequest {
            memberAPI.memberList(id)
        }
    }

    suspend fun MemberDetails(id: String): GetMemberDetailsResponse {
        return apiRequest {
            memberAPI.MemberDetails(id)
        }
    }

    //update Product
    suspend fun updateMember(id:String, member: Member):AddNewMemberResponse{
        return apiRequest {
            memberAPI.updateMember(id, member)
        }
    }



}