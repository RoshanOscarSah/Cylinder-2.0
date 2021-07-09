package com.eachut.cylinder.response

import com.eachut.cylinder.entity.User

data class ChangePasswordResponse (
    val success : Boolean?=null,
    val user : User?=null,
    val message : String?=null
        )