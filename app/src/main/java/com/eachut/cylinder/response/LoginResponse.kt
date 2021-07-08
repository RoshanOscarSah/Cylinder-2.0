package com.eachut.cylinder.response

import com.eachut.cylinder.entity.User

data class LoginResponse (
    val success : Boolean? = null,
    val message: String?=null,
    val token : String ? =null,
    val user : User ? =null,
        )