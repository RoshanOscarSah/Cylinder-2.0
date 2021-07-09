package com.eachut.cylinder.entity

import androidx.room.Entity


//@Entity

data class User (
    var _id : String ? = null,
    var first_name : String? = null,
    var last_name  :String?=null,
    var phone_number : String ? =null,
    var username : String?=null,
    var password : String ? =null,
    var status : String?=null,
    var change_password : Boolean?=null,
    var isActive : Boolean?=null,
    var isAdmin : Boolean?=null,
    var address : String?=null,
    var comission_percent : String?=null,
    var createdAt : String?=null,
        )