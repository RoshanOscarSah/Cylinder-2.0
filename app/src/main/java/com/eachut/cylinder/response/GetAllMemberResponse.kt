package com.eachut.cylinder.response

import com.eachut.cylinder.entity.Member

data class GetAllMemberResponse (
    val success:Boolean?=null,
    val message:String?=null,
    val data: MutableList<Member>?=null
)