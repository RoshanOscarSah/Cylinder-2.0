package com.eachut.cylinder.response

import com.eachut.cylinder.entity.Company


data class AddNewCompanyResponse (
    val success : Boolean? = null,
    val message: String?=null,
    val company: Company? =null
)