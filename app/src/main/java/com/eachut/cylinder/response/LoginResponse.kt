package com.eachut.cylinder.response

import com.eachut.cylinder.entity.Member

data class LoginResponse (
    val success : Boolean? = null,
    val message: String?=null,
    val token : String ? =null,
    val member: Member? =null,
    val status: String? =null
        )