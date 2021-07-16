package com.eachut.cylinder.response

import com.eachut.cylinder.entity.Reseller

data class GetResellerResponse (
    val success:Boolean?=null,
    val message:String?=null,
    val data: MutableList<Reseller>?=null
)