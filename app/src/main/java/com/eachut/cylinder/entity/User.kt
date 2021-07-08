package com.eachut.cylinder.entity

import androidx.room.Entity


//@Entity

data class User (
    var _id : String ? = null,
    var Firstname : String? = null,
    var Lastname  :String?=null,
    var Phonenumber : String ? =null,
    var Username : String?=null,
    var Password : String ? =null,
    var Status : String?=null,
    var Address : String?=null,
    var Comission : String?=null,
    var AccountCreated : String?=null,
        )