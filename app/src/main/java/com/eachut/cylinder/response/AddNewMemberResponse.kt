package com.eachut.cylinder.response

import com.eachut.cylinder.entity.User

data class AddNewMemberResponse (
    val password:String?=null,
    val success : Boolean? = null,
    val message: String?=null,
    val user : User? =null,
        )