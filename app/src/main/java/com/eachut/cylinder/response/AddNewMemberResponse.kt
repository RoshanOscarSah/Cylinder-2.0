package com.eachut.cylinder.response

import com.eachut.cylinder.entity.Member


data class AddNewMemberResponse (
    val success : Boolean? = null,
    val message: String?=null,
    val username: String? =null,
    val password:String?=null,
        )