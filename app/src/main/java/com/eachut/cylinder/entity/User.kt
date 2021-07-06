package com.eachut.cylinder.entity

import androidx.room.Entity


@Entity

data class User (
    var _id : String?=null,
    var Fullname : String ? =null,
    var Phonenumber : String ? =null,
    var Password : String ? =null,
    var Usertype : String ? =null
        )