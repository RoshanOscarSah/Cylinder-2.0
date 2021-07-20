package com.eachut.cylinder.response

import com.eachut.cylinder.entity.Company

data class GetCompanyResponse (
    val success:Boolean?=null,
    val message:String?=null,
    val company: Company?=null
)