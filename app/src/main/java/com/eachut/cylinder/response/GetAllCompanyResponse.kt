package com.eachut.cylinder.response

import com.eachut.cylinder.entity.Company

data class GetAllCompanyResponse (
    val success:Boolean?=null,
    val message:String?=null,
    val data: MutableList<Company>?=null
)