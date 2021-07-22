package com.eachut.cylinder.response

import com.eachut.cylinder.entity.Member

data class GetMemberResponse (
    val success:Boolean?=null,
    val message:String?=null,
    val member: Member?= null
)